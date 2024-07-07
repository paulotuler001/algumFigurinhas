package services;

import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import entities.LittleFigure;
import repositories.LittleFigureRepository;

public class LittleFigureService {
	
	LittleFigureRepository lfr = new LittleFigureRepository();
	
	public void saveLF(LittleFigure lf) {
		
		if(lf.getName().length() < 1) 
			throw new ExceptionService("Digite um nome válido");
		else if(lf.getId() < 1)
			throw new ExceptionService("Digite um id válido");
		else if(lf.getNumber() < 0) 
			throw new ExceptionService("Digite um número válido");
		
		lfr.saveLF(lf);
	}
	
	public LittleFigure getLFById(Integer id) {
		
		if(id < 1) 
			throw new ExceptionService("Digite um id inteiro válido");
		
		return lfr.getLFById(id);
	}
	
	public Object[][] getAllLittleFigures() {
		return lfr.getAllLittleFigures();
	}
	
	public void deleteLFById(Integer id) {
		
		if(id < 1) 
			throw new ExceptionService("Digite um id inteiro válido");
		else if(lfr.getLFById(id) == null)
			throw new ExceptionService("Figurinha não encontrada");
		
		lfr.deleteLFById(id);
		
	}
	
	public int deleteAllLFs() {
		
		int response = JOptionPane.showConfirmDialog(null, "Você realmente quer deletar tudo?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            int secondResponse = JOptionPane.showConfirmDialog(null, "Tem certeza? TUDO será deletado!", "Confirmação Final", JOptionPane.YES_NO_OPTION);

            if (secondResponse == JOptionPane.YES_OPTION) {
                lfr.deleteAllLFs();
                JOptionPane.showMessageDialog(null, "Todos os registros foram deletados com sucesso.");
                return 1;
            }
        }
        return 0;
	}
	
	public void editLFById(Integer id, LittleFigure lf) {
		
		if(id < 1) 
			throw new ExceptionService("Digite um id inteiro válido");
		else if(lfr.getLFById(id) == null) 
			throw new ExceptionService("Figurinha não encontrada");
		else if(lf.getName().length()<1) 
			throw new ExceptionService("Digite um nome válido");
		
		lfr.editLFById(id, lf);
		
	}
	
	public ArrayList<ImageIcon> getAllPhotos(){
		return lfr.getAllPhotos();
	}
	
}
