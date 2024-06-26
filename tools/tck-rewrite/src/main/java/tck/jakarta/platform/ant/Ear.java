package tck.jakarta.platform.ant;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.RuntimeConfigurable;

import java.util.Hashtable;

public class Ear extends BaseJar {
    boolean deletecomponentarchives;
    FileSet modules;
    FileSet libs;

    public Ear(Project project, RuntimeConfigurable taskRC) {
        super(project, taskRC);
        Hashtable<String,Object> attrs = taskRC.getAttributeMap();
        Object flag = attrs.get("deletecomponentarchives");
        if(flag != null) {
            this.deletecomponentarchives = Boolean.parseBoolean(flag.toString());
        }

    }

    public String getType() {
        return "EAR";
    }
    public boolean isDeleteComponentArchives() {
        return deletecomponentarchives;
    }
}
