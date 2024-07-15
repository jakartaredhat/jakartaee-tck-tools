package tck.conversion.ant.api;

import com.sun.ts.tests.jms.core.bytesMsgTopic.BytesMsgTopicTests;

import com.sun.ts.tests.signaturetest.javaee.JavaEESigTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import tck.jakarta.platform.ant.api.DeploymentMethodInfo;
import tck.jakarta.platform.ant.api.DeploymentMethodInfoBuilder;
import tck.jakarta.platform.vehicles.VehicleType;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * These tests only run if there is ts.home property set. The ts.home property needs to be set to an EE10 TCK
 * distribution and a glassfish7 distribution unbundled as a peer directory:
 * workingdir/glassfish7
 * workingdir/jakartaeetck
 *
 * They also require the EE 11 TCK tests, so they need to be run with the dependencies defined in the
 * deploymentmethod-tests profile.
 */
@EnabledIfSystemProperty(named = "ts.home", matches = ".*")
public class DeploymentMethodTest {
    static Path tsHome = Paths.get(System.getProperty("ts.home"));

    @Test
    public void testBytesMsgTopicTest_deployAppclientMethod() throws IOException {
        DeploymentMethodInfoBuilder builder = new DeploymentMethodInfoBuilder(tsHome);
        DeploymentMethodInfo deployMethod = builder.forTestClassAndVehicle(BytesMsgTopicTests.class, VehicleType.appclient);
        System.out.printf("--- DeployMethod(%s):\n%s\n---\n", deployMethod.getVehicle(), deployMethod.getMethodCode());
    }

    @Test
    public void testBytesMsgTopicTest_deployEjbMethod() throws IOException {
        DeploymentMethodInfoBuilder builder = new DeploymentMethodInfoBuilder(tsHome);
        DeploymentMethodInfo deployMethod = builder.forTestClassAndVehicle(BytesMsgTopicTests.class, VehicleType.ejb);
        System.out.printf("--- DeployMethod(%s):\n%s\n---\n", deployMethod.getVehicle(), deployMethod.getMethodCode());
    }

    @Test
    public void testBytesMsgTopicTest_deployMethods() throws IOException {
        DeploymentMethodInfoBuilder builder = new DeploymentMethodInfoBuilder(tsHome);
        List<DeploymentMethodInfo> deployMethods = builder.forTestClass(BytesMsgTopicTests.class);
        for (DeploymentMethodInfo deployMethod : deployMethods) {
            System.out.printf("--- DeployMethod(%s):\n%s\n---\n", deployMethod.getVehicle(), deployMethod.getMethodCode());
        }
    }

    @Test
    public void testJavaEESigTest_deployAppclientMethod() throws IOException {
        DeploymentMethodInfoBuilder builder = new DeploymentMethodInfoBuilder(tsHome);
        DeploymentMethodInfo deployMethod = builder.forTestClassAndVehicle(JavaEESigTest.class, VehicleType.appclient);
        for(String imp : deployMethod.getImports()) {
            System.out.printf("import %s;\n", imp);
        }
        System.out.printf("--- DeployMethod(%s):\n%s\n---\n", deployMethod.getVehicle(), deployMethod.getMethodCode());
    }

    @Test
    public void testJavaEESigTest_deployServletMethod() throws IOException {
        DeploymentMethodInfoBuilder builder = new DeploymentMethodInfoBuilder(tsHome);
        DeploymentMethodInfo deployMethod = builder.forTestClassAndVehicle(JavaEESigTest.class, VehicleType.servlet);
        System.out.println(deployMethod);
    }

    @Test
    public void testJavaEESigTest_deployJspMethod() throws IOException {
        DeploymentMethodInfoBuilder builder = new DeploymentMethodInfoBuilder(tsHome);
        DeploymentMethodInfo deployMethod = builder.forTestClassAndVehicle(JavaEESigTest.class, VehicleType.jsp);
        System.out.println(deployMethod);
    }

    @Test
    public void testJavaEESigTest_deployEjbMethod() throws IOException {
        DeploymentMethodInfoBuilder builder = new DeploymentMethodInfoBuilder(tsHome);
        DeploymentMethodInfo deployMethod = builder.forTestClassAndVehicle(JavaEESigTest.class, VehicleType.ejb);
        System.out.println(deployMethod);
    }
}