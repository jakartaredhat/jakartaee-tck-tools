package tck.jakarta.platform.ant;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.RuntimeConfigurable;

public class Rar extends BaseJar {

    public Rar(Project project, RuntimeConfigurable taskRC) {
        super(project, taskRC);
    }

    @Override
    public String getType() {
        return "Rar";
    }
}
