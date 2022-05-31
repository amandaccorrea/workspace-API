package com.residencia.comercio.services;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArquivoService {
	//@Value("${pasta.arquivos.imagem}")
	private String diretorioArquivos = "C:\\\\API\\\\imagens";
	private Path localFinalArquivo;
	//@Autowired
	//EmailService emailService;
	
	public void criarArquivo(String fileName, MultipartFile file) throws Exception{
		try {
			if(fileName.contains("..")) {
				throw new Exception("Nome do arquivo inválido.");
			}
			this.localFinalArquivo = Paths.get(diretorioArquivos).toAbsolutePath().normalize();
			
			Path destinoLocation = localFinalArquivo.resolve(fileName);
			Files.copy(file.getInputStream(), destinoLocation, StandardCopyOption.REPLACE_EXISTING);
			
			//String corpoEmail= "Foi cadastrada uma nova categoria"+ emailService.toString();
			//emailService.enviarEmailTexto("teste@teste.com","Cadastro de Categtoria", corpoEmail);
		
	}catch(IOException ex){
		throw new IOException("Não foi possivel mover o arquivo. - " + ex.getStackTrace());
	}
	}
	
	
}
