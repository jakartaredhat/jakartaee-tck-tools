package tck.conversion.ant.st4;

import com.sun.ts.tests.ejb.ee.bb.session.lrapitest.B;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/**
 * Tests of stringtemplate4 features
 * https://github.com/antlr/stringtemplate4/blob/master/doc/cheatsheet.md
 */
public class StringTemplateTests {
    STGroup testGroup = new STGroupFile("StringTemplate4Tests.stg");
    @Test
    public void testIf() {
        //System.out.println(ejbJarGroup.show());
        ST template = testGroup.getInstanceOf("testIf");
        template.add("attr1", "attr1Value");
        template.add("attr2", "attr2Value");
        String out = template.render().trim();
        Assertions.assertEquals("Saw attr1Value", out);

        template.remove("attr1");
        out = template.render().trim();
        Assertions.assertEquals("Saw attr2Value", out);

        template.remove("attr2");
        out = template.render().trim();
        Assertions.assertEquals("Saw default", out);

    }

    @Test
    public void testAltIf() {
        // (!a||b)&&!(c||d) == broken else works
        ST template = testGroup.getInstanceOf("testAltIf");
        template.add("a", Boolean.TRUE);
        template.add("b", Boolean.FALSE);
        template.add("c", Boolean.FALSE);
        template.add("d", Boolean.FALSE);
        String out = template.render().trim();
        Assertions.assertEquals("works", out);
        template.remove("a");
        out = template.render().trim();
        Assertions.assertEquals("broken", out);

        // Just a
        template = testGroup.getInstanceOf("testAltIf");
        template.add("a", "value");
        out = template.render().trim();
        Assertions.assertEquals("works", out);
        // Just b
        template = testGroup.getInstanceOf("testAltIf");
        template.add("b", "value");
        out = template.render().trim();
        Assertions.assertEquals("broken", out);
        // Just c
        template = testGroup.getInstanceOf("testAltIf");
        template.add("c", "value");
        out = template.render().trim();
        Assertions.assertEquals("works", out);
        // Just d
        template = testGroup.getInstanceOf("testAltIf");
        template.add("d", "value");
        out = template.render().trim();
        Assertions.assertEquals("works", out);
    }

    @Test
    public void testMethod() {
        ST template = testGroup.getInstanceOf("method");
        template.add("name", "multx2");
        template.add("body", "x*=2;");
        template.add("return", "return x;");
        template.add("arg", "x");
        String out = template.render().trim();
        System.out.println(out);
        String expected = """
int multx2(int x) {
  x*=2;
  return x;
}
""";
        Assertions.assertEquals(expected.trim(), out);
    }

    @Test
    public void testMult2x() {
        ST template = testGroup.getInstanceOf("mult2x");
        template.add("arg", "x");
        String out = template.render().trim();
        System.out.println(out);
        String expected = """
int mult2x(int x) {
  x*=2;
  return x;
}
""";
        Assertions.assertEquals(expected.trim(), out);
    }
}
