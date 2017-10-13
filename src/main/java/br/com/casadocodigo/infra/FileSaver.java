package br.com.casadocodigo.infra;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;

import javax.servlet.http.Part;

public class FileSaver {

	public static final String SERVER_PATH = "/casadocodigo";
	
	public String write(Part arquivo, String path) {
		String relativePath =  path + "/" + arquivo.getSubmittedFileName();
        try {
            arquivo.write(SERVER_PATH + "/" + relativePath);

            return relativePath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}

	public static void transfer(Path source, OutputStream outputStream) {
		try {
	        FileInputStream input = new FileInputStream(source.toFile());
	        
	        //o try-with-resources automaticamente já fecha um recurso após o fim do try
	        try( ReadableByteChannel inputChannel = Channels.newChannel(input); 
	                  WritableByteChannel outputChannel = Channels.newChannel(outputStream)) {
	        	
	        	  // transferência de um lado para o outro deve ser feita sempre usando um Buffer
	        	  //10kb por vez
	              ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 10);

            //lendo o canal de entrada e transferindo para p buffer
	        while(inputChannel.read(buffer) != -1) {
	        	//o método buffer.flip que colocará o ponteiro na posição zero novamente
	        	buffer.flip();
	        	
	        	//Com os bytes no buffer, podemos enviar para o outputChannel
	        	//só que dessa vez não queremos ler, e sim escrever na saída
	            outputChannel.write(buffer);
	            
	            //limpando o buffer
	            buffer.clear();
	        }
	        } catch (IOException e) {
	            throw new RuntimeException(e); 
	        }
	    } catch (FileNotFoundException e) {
	        throw new RuntimeException(e); 
	    }
		
	}
}
