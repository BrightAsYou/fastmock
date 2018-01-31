package org.brightasu.fastmock;

import org.apache.commons.cli.*;

/**
 * Created by BrightAsU on 2018/1/4.
 */
public class CommandLineDemo {

    /**
     * @param args
     */
    public static void TESTOP(String[] args) {
        Options opt = new Options();
        opt.addOption("p", false, "no error if existing, " +
                "make parent directories as needed.");
        opt.addOption("v", "verbose", false, "explain what is being done.");
        opt.addOption(OptionBuilder.withArgName("file")
                .hasArg()
                .withDescription("search for buildfile towards the root of the filesystem and use it")
                .create("O"));
        opt.addOption(OptionBuilder.withLongOpt("block-size")
                .withDescription("use SIZE-byte blocks")
                .withValueSeparator('=')
                .hasArg()
                .create() );
        opt.addOption("h", "help",  false, "print help for the command.");

        String formatstr = "gmkdir [-p][-v/--verbose][--block-size][-h/--help] DirectoryName";

        HelpFormatter formatter = new HelpFormatter();
        CommandLineParser parser = new PosixParser();
        CommandLine cl = null;
        try {
            // 处理Options和参数
            cl = parser.parse( opt, args );
        } catch (ParseException e) {
            formatter.printHelp( formatstr, opt ); // 如果发生异常，则打印出帮助信息
        }
        // 如果包含有-h或--help，则打印出帮助信息
        if (cl.hasOption("h")) {
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp(formatstr, "++++++++++++++++rfh+++++++++++++++++++", opt, "++++++++++++++++solo+++++++++++++++++++");
            return;
        }
        // 判断是否有-p参数
        if (cl.hasOption("p")) {
            System.out.println("has p");
        }
        // 判断是否有-v或--verbose参数
        if (cl.hasOption("v")) {
            System.out.println("has v");
        }
        // 获取参数值，这里主要是DirectoryName
        String[] str = cl.getArgs();
        int length = str.length;
        System.out.println("length="+length);
        System.out.println("Str[0]="+str[0]);
        //判断是否含有block-size参数
        if( cl.hasOption( "block-size" ) ) {
            // print the value of block-size
            System.out.println("block-size=" + cl.getOptionValue("block-size"));
        }
    }


    // 实现   启动服务 hiboot [d/debug] [a/alias <aliasName>] [size=123] [h/help]
    public static void myfirstCommandLine(String [] str) {

        Options opt = new Options();
        opt.addOption("d","debug",false,"if required debug").
                addOption("a","alias",true,"if required alias").
                addOption(OptionBuilder.withLongOpt("size").withDescription("size required").withValueSeparator('=').withArgName("size").create()).
                addOption("h","help",false,"help me").
                addOption(OptionBuilder.withArgName("file").hasArg().create("O"));


        String helpFormate = "hiboot [-d/--debug] [-a/--alias] [--size] [-h/--help]";
        HelpFormatter helpFormatter = new HelpFormatter();
        CommandLineParser parser = new PosixParser();
        CommandLine commandLine = null;
        try {
            commandLine = parser.parse(opt, str);
        } catch (ParseException e) {
            helpFormatter.printHelp(helpFormate,opt);
            return;
        }
        if (commandLine.hasOption("h")) {
            helpFormatter.printHelp(helpFormate,opt);
        }

        if (commandLine.hasOption('d')){
            System.out.println("debug");
        }
        if (commandLine.hasOption('a')){
            System.out.println("alias  "+commandLine.getOptionValue('a'));
        }

        if (commandLine.hasOption("size")){
            System.out.println("size" + commandLine.getOptionValue("size"));
        }

        if (commandLine.hasOption("O")){
            System.out.println("O" );
        }


    }

    public static void main(String[] args) {
//        String [] testStr = {"gmkdir","--block-size=100","hello"};
//        TESTOP(testStr);
        String [] testStr = {"hiboot","-a","hello","--debug","--size","123","-O","filename"};
//        hiboot [d/debug] [a/alias <aliasName>] [size=123] [h/help]
        myfirstCommandLine(testStr);
    }

}
