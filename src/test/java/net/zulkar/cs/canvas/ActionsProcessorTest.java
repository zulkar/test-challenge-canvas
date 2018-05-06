package net.zulkar.cs.canvas;

import net.zulkar.cs.canvas.actions.Quit;
import net.zulkar.cs.canvas.command.CommandSupplier;
import net.zulkar.cs.canvas.draw.Drawer;
import net.zulkar.cs.canvas.dto.Action;
import net.zulkar.cs.canvas.parser.Parser;
import net.zulkar.cs.canvas.render.Renderer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ActionsProcessorTest {

    @Mock
    private CommandSupplier commandSupplier;

    @Mock
    private Renderer renderer;

    @Mock
    private Parser parser;

    private ActionsProcessor application;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        application = new ActionsProcessor();
        application.init(commandSupplier, parser, renderer);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotRunWithoutInit() {
        new ActionsProcessor().run();
    }

    @Test
    public void shouldNotDrawActionsIfCanvasNotInitialized() {
        Action draw = mock(Action.class);
        when(draw.getType()).thenReturn(Action.Type.DRAW);
        givenAction(draw);
        application.run();
        verify(draw, never()).execute(application);
    }

    @Test
    public void shouldExecuteAction() {
        Action action = mock(Action.class);
        givenAction(action);
        application.run();
        verify(action).execute(application);
    }

    @Test
    public void shouldCloseRenderOnQuit() {
        Action action = mock(Action.class);
        givenAction(action);
        application.run();
        verify(renderer).close();
    }

    @Test
    public void shouldRenderAfterActions() {
        Action draw = mock(Action.class);
        givenAction(draw);
        Drawer drawer = mock(Drawer.class);
        application.setDrawer(drawer);
        application.run();

        verify(renderer).render(drawer);
    }

    @Test
    public void shouldNotRenderAfterQuit() {
        Action action = new Quit();
        givenAction(action);
        Drawer drawer = mock(Drawer.class);
        application.setDrawer(drawer);
        application.run();
        verify(renderer, never()).render(drawer);
    }

    @Test
    public void shouldNotFailOnExceptions() {
        Action action = mock(Action.class);
        doThrow(new RuntimeException()).when(action).execute(any());
        givenAction(action);
        application.run();
    }


    private void givenAction(Action action) {
        when(commandSupplier.getNextCommand()).thenReturn(Optional.of("TEST")).thenReturn(Optional.empty());
        when(parser.parse("TEST")).thenReturn(action);
    }

}