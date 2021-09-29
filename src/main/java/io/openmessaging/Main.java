package io.openmessaging;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;




public class Main {
    public static void main(String args[]) throws IOException{
        System.out.println("Hello World!");

        String metaPath = "/home/ubuntu/ContestForAli/pmem_test_llpl/MetaData";
        String dataPath = "/home/ubuntu/ContestForAli/pmem_test_llpl/data";
        
        FileChannel fileChannel = new RandomAccessFile(new File(dataPath), "rw").getChannel();
        FileChannel metaFileChannel = new RandomAccessFile(new File(metaPath), "rw").getChannel();

        SSDqueue ssdQueue = new SSDqueue(fileChannel, metaFileChannel);
        String t = "1234545";
        ByteBuffer tmp = ByteBuffer.wrap(t.getBytes());
        ssdQueue.setTopic("12345", 123, tmp);
        ssdQueue.setTopic("12345", 123, ByteBuffer.wrap(t.getBytes()));
        ssdQueue.setTopic("12345", 123, ByteBuffer.wrap(t.getBytes()));
        //ssdQueue.setTopic("dsfsf", 123, tmp);

        Map<Integer, ByteBuffer> res = ssdQueue.getRange("12345", 123, 0L, 5);

        for(Map.Entry<Integer, ByteBuffer> entry: res.entrySet()){
            System.out.println("" + entry.getKey() + " : " + new String(entry.getValue().array()));
        }
        

        metaFileChannel.close();
        fileChannel.close();
    
    }

}

