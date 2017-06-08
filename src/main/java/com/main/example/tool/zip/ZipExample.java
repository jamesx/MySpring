package com.main.example.tool.zip;

import org.apache.commons.io.IOUtils;
import org.zeroturnaround.zip.*;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by xsooy-pc on 2017/5/27.
 */
public class ZipExample {
    public static void main(String[] args){
       // 检查zip中是否包含某个文件

        boolean exists = ZipUtil.containsEntry(new File("/tmp/demo.zip"), "foo.txt");
       // 将zip中的某个文件解压成数组

        byte[] bytes1 = ZipUtil.unpackEntry(new File("/tmp/demo.zip"), "foo.txt");
      //  以指定的编码将zip中的某个文件解压成数组

        byte[] bytes2 = ZipUtil.unpackEntry(new File("/tmp/demo.zip"), "foo.txt", Charset.forName("IBM437"));
       // 将zip中的某个文件解压到指定路径

        ZipUtil.unpackEntry(new File("/tmp/demo.zip"), "foo.txt", new File("/tmp/bar.txt"));
       // 解压zip文件

        ZipUtil.unpack(new File("/tmp/demo.zip"), new File("/tmp/demo"));
       // 将zip名称作为解压目录

        ZipUtil.explode(new File("/tmp/demo.zip"));
       // 从zip中解压某个文件夹

        ZipUtil.unpack(new File("/tmp/demo.zip"), new File("/tmp/demo"), new NameMapper() {
            public String map(String name) {
                return name.startsWith("doc/") ? name : null;
            }
        });
       // 从zip中提取不包括目录名称的目录

        final String prefix = "doc/";
        ZipUtil.unpack(new File("/tmp/demo.zip"), new File("/tmp/demo"), new NameMapper() {
            public String map(String name) {
                return name.startsWith(prefix) ? name.substring(prefix.length()) : name;
            }
        });

       // 压缩

        //压缩某个文件夹

        ZipUtil.pack(new File("/tmp/demo"), new File("/tmp/demo.zip"));
       // 压缩与zip名称相同的目录

        ZipUtil.unexplode(new File("/tmp/demo.zip"));
       // 将文件压缩到某个父目录下

        ZipUtil.pack(new File("/tmp/demo"), new File("/tmp/demo.zip"), new NameMapper() {
            public String map(String name) {
                return "foo/" + name;
            }
        });
       // 向zip中添加某个文件

        ZipUtil.addEntry(new File("/tmp/demo.zip"), "doc/readme.txt", new File("f/tmp/oo.txt"), new File("/tmp/new.zip"));
      //  将某个byte数组添加至zip

        ZipUtil.addEntry(new File("/tmp/demo.zip"), "doc/readme.txt", "bar".getBytes(), new File("/tmp/new.zip"));
       // 将文件或者数组添加到zip

        ZipEntrySource[] entries1 = new ZipEntrySource[] {
                new FileSource("doc/readme.txt", new File("foo.txt")),
                new ByteSource("sample.txt", "bar".getBytes())
        };
        ZipUtil.addEntries(new File("/tmp/demo.zip"), entries1, new File("/tmp/new.zip"));
        //从文件或者数组添加到输出流

        ZipEntrySource[] entries2 = new ZipEntrySource[] {
                new FileSource("doc/readme.txt", new File("foo.txt")),
                new ByteSource("sample.txt", "bar".getBytes())
        };
        OutputStream out = null;
        try {
            try {
                out = new BufferedOutputStream(new FileOutputStream(new File("/tmp/new.zip")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ZipUtil.addEntries(new File("/tmp/demo.zip"), entries2, out);
        }
        finally {
            IOUtils.closeQuietly(out);
        }
       // 用文件替换zip中的文件

        boolean replaced1 = ZipUtil.replaceEntry(new File("/tmp/demo.zip"), "doc/readme.txt", new File("/tmp/foo.txt"), new File("/tmp/new.zip"));
       // 用数组替换zip中的文件

        boolean replaced2 = ZipUtil.replaceEntry(new File("/tmp/demo.zip"), "doc/readme.txt", "bar".getBytes(), new File("/tmp/new.zip"));
      //  用文件或数组替换zip中的文件

        ZipEntrySource[] entries = new ZipEntrySource[] {
                new FileSource("doc/readme.txt", new File("foo.txt")),
                new ByteSource("sample.txt", "bar".getBytes())
        };
        boolean replaced3 = ZipUtil.replaceEntries(new File("/tmp/demo.zip"), entries, new File("/tmp/new.zip"));
       // 添加或替换zip中的文件

        ZipEntrySource[] addedEntries = new ZipEntrySource[] {
                new FileSource("/path/in/zip/File1.txt", new File("/tmp/file1.txt")),
                new FileSource("/path/in/zip/File2.txt", new File("/tmp/file2.txt")),
                new FileSource("/path/in/zip/File3.txt", new File("/tmp/file2.txt")),
        };
        ZipUtil.addOrReplaceEntries(new File("/tmp/demo.zip"), addedEntries);
    }
}
