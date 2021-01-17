package com.MrS.possible.Service;

import com.MrS.possible.domain.Member;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class PythonServiceImpl implements PythonService{

    public static void execPython(String[] command) throws IOException, InterruptedException {
        CommandLine commandLine = CommandLine.parse(command[0]);
        for (int i = 1, n = command.length; i < n; i++) {
            commandLine.addArgument(command[i]);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream);
        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(pumpStreamHandler);
        int result = executor.execute(commandLine);
        System.out.println("result: " + result);  // check request
        System.out.println("output: " + outputStream.toString());

    }

    // method override & recommend python script compute
    @Override
    public String recommend(Member member) {
        System.out.println("Python Call");

        String[] command = new String[4];
        command[0] = "python";
        command[1] = "/Users/jinwoo/IdeaProjects/yscec/possible/src/main/resources/python_recommend.py";   // you need to input Local Root python_recommend.py!!!
        command[2] = "-i " + member.getId();  // Type : long to String
        command[3] = "-a" + member.getAccount();
        try {
            execPython(command);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(member.getId() + member.getAccount());

//        String cmds = "sh /Users/jinwoo/PycharmProjects/code_test/python_recommend.py";
//        String[] callCmd = {"/bin/bash", "-i", id};
//        Map map = SR.execCommand(callCmd);
//
//        System.out.println(map);

        return null;

    }
}





//class ShRunner {
//            public Map<Integer, String> execCommand(String... str) {
//                Map<Integer, String> map = new HashMap<>();
//                ProcessBuilder pb = new ProcessBuilder(str);
//                pb.redirectErrorStream(true);
//                Process process = null;
//                try {
//                    process = pb.start();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                BufferedReader reader = null;
//                if (process != null) {
//                    reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//                }
//
//                String line;
//                StringBuilder stringBuilder = new StringBuilder();
//                try {
//                    if (reader != null) {
//                        while ((line = reader.readLine()) != null) {
//                            stringBuilder.append(line).append("\n");
//                        }
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                try {
//                    if (process != null) {
//                        process.waitFor();
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                if (process != null) {
//                    map.put(0, String.valueOf(process.exitValue()));
//                }
//
//                try {
//                    map.put(1, stringBuilder.toString());
//                } catch (StringIndexOutOfBoundsException e) {
//                    if (stringBuilder.toString().length() == 0) {
//                        return map;
//                    }
//                }
//                return map;
//            }
//        }

//        ShRunner SR = new ShRunner();