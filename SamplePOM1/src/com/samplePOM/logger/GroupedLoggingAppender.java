package com.samplePOM.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

public class GroupedLoggingAppender extends AppenderSkeleton implements IReporter{

	private final ConcurrentHashMap<Long, BufferedWriter> tid2File = new ConcurrentHashMap<Long, BufferedWriter>();
	private final String outputDir;
	private final String outputFile;
	private final String ext = ".threadlog.txt";
	private DateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public GroupedLoggingAppender(){
		String projectDir = System.getProperty("user.dir");
		String outDir = projectDir+File.separator+"test-output"+File.separator+"Logs";
		if(!outDir.endsWith(File.separator)){
			outDir += File.separator;
		}
		outputDir = outDir;
		outputFile = outputDir+"output.log.grouped.txt";
		try{
			if(outputDir != null){
				Files.deleteIfExists(FileSystems.getDefault().getPath(outputFile));
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean requiresLayout() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1,
			String arg2) {
		System.out.println("Reported getting called::"+outputDir);
		mergeLogFiles();
	}

	public void mergeLogFiles(){
		try{
			File file = new File(outputDir);
			File[] files = file.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return pathname.getName().endsWith(ext);
				}
			});
			List<Path> paths = new ArrayList<Path>();
			for(File f:files){
				Path path = f.toPath();
				paths.add(path);
			}
			Collections.sort(paths);
			Path pathAll = FileSystems.getDefault().getPath(outputFile);
			for(Path path:paths){
				Files.write(pathAll, Files.readAllBytes(path), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
				//Files.delete(path);
			}
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	@Override
	protected void append(LoggingEvent event) {
		String dateString = dformat.format(new Date(event.getTimeStamp()));
		if(outputDir == null)
			return;
		try{
			long tid = Thread.currentThread().getId();
			BufferedWriter fw = tid2File.get(tid);
			if(fw == null){
				fw = new BufferedWriter(new FileWriter(getFileNameFromThread(tid)));
				tid2File.put(tid, fw);
			}
			fw.write(String.valueOf(tid)+"["+event.getThreadName()+"]"+dateString+" "+event.getLoggerName()+" "+event.getMessage().toString());
			fw.newLine();
			fw.flush();
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	private String getFileNameFromThread(long tid){
		return String.format("%sthread_output_%04d%s", outputDir, tid, ext);
	}
}