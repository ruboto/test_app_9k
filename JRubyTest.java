public class JRubyTest {
    public static void main(String... args) {
        new JRubyTest().test();
    }

    private void test() {
        System.setProperty("jruby.backtrace.style", "normal"); // normal raw full mri
        System.setProperty("jruby.bytecode.version", "1.6");
        System.setProperty("jruby.compat.version", "RUBY2_2"); // RUBY1_9 is the default in JRuby 1.7
        System.setProperty("jruby.compile.mode", "OFF"); // OFF OFFIR JITIR? FORCE FORCEIR
        System.setProperty("jruby.interfaces.useProxy", "true");
        System.setProperty("jruby.ir.passes", "LocalOptimizationPass,DeadCodeElimination");
        System.setProperty("jruby.management.enabled", "false");
        System.setProperty("jruby.native.enabled", "false");
        System.setProperty("jruby.objectspace.enabled", "false");
        System.setProperty("jruby.rewrite.java.trace", "true");
        // System.setProperty("jruby.thread.pooling", "true");
        // System.setProperty("jruby.ji.proxyClassFactory", "org.ruboto.DalvikProxyClassFactory");
        System.setProperty("jruby.ji.upper.case.package.name.allowed", "true");
        // System.setProperty("jruby.class.cache.path", appContext.getDir("dex", 0).getAbsolutePath());
        // System.setProperty("java.io.tmpdir", appContext.getCacheDir().getAbsolutePath());

        org.jruby.embed.ScriptingContainer c = new org.jruby.embed.ScriptingContainer(org.jruby.embed.LocalContextScope.CONCURRENT, org.jruby.embed.LocalVariableBehavior.TRANSIENT);

        c.put("MyConstant", c.runScriptlet("Java::Default::JRubyTest"));
        c.runScriptlet("puts MyConstant");
        c.runScriptlet("class MyConstant ; def myMethod ; puts 'myMethod called' ; end ; end");
        c.runRubyMethod(Object.class, this, "myMethod");
    }
}