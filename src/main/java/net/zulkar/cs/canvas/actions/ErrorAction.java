package net.zulkar.cs.canvas.actions;

import net.zulkar.cs.canvas.ActionsProcessor;
import net.zulkar.cs.canvas.dto.Action;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class ErrorAction implements Action {

    private final String desc;
    private final Throwable error;

    public ErrorAction(String desc) {
        this.desc = desc;
        error = null;
    }

    public ErrorAction(String desc, Throwable error) {
        this.desc = desc + ":" + error.getMessage();
        this.error = error;
    }

    @Override
    public void execute(ActionsProcessor actionsProcessor) {
        actionsProcessor.getRenderer().println(desc);
    }

    @Override
    public Type getType() {
        return Type.APPLICATION;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .append("desc", desc)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorAction that = (ErrorAction) o;
        return Objects.equals(desc, that.desc);
    }

    @Override
    public int hashCode() {

        return Objects.hash(desc);
    }


}
