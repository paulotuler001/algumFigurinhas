package Principal;

import LittleFigures.LittleFigure;
import LittleFigures.LittleFigureRepository;

public class Main {
	public static void main(String[] args) {
		LittleFigure lFigure = new LittleFigure(6, "Paulo");
		LittleFigure lFigure2 = new LittleFigure(5, "Pedro");
		LittleFigure lFigure3 = new LittleFigure(5, "Penis");
		
		LittleFigureRepository lfRep = new LittleFigureRepository();
		
		lfRep.createTable();
//		lfRep.saveLF(lFigure);
//		lfRep.deleteLFById(1);
//		lfRep.saveLF(lFigure3);
//		lfRep.saveLF(lFigure2);
//		lfRep.getLFById(2);
////		lfRep.editLFById(2, lFigure3);
		lfRep.getLFById(5);
		lfRep.getLFById(6);
		lfRep.getAllLittleFigures();
	}
}