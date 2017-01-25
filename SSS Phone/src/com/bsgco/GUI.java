package com.bsgco;

import static javax.swing.JOptionPane.showMessageDialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class GUI {
	private final JFileChooser jfc = new JFileChooser();
	
	private final JFileChooser jfc2 = new JFileChooser();
	
	public GUI() {
		jfc.setMultiSelectionEnabled(false);
		jfc.setFileFilter(new FileNameExtensionFilter("XML Files", "xml"));
		if(JFileChooser.APPROVE_OPTION != jfc.showOpenDialog(null)) {
			showMessageDialog(null, "Thank you.");
			System.exit(0);
		}
		File sel = jfc.getSelectedFile();
		if(null == sel) {
			showMessageDialog(null, "Thank you.");
			System.exit(0);
		}
		
		jfc2.setMultiSelectionEnabled(false);
		jfc2.setFileFilter(new FileNameExtensionFilter("XML Files", "xml"));
		jfc2.showSaveDialog(null);
		File file = jfc2.getSelectedFile();
		
		if(file != null) {
			String path = file.getAbsolutePath().toLowerCase();
			if(!path.endsWith(".xml"))
				file = new File(file.getAbsolutePath() + ".xml");
			try {
				Editor e = new Editor(readFile(sel));
				writeFile(file, e.toString());
				showMessageDialog(null, "Done.");
			} catch(Exception e) {
				e.printStackTrace();
				showMessageDialog(null, e.getMessage());
			}
		}
		else
			showMessageDialog(null, "Thank you.");
	}
	
	private String readFile(File file) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line;
		boolean first = true;
		while((line = in.readLine()) != null) {
			if(first)
				first = false;
			else
				sb.append('\n');
			sb.append(line);
		}
		in.close();
		return sb.toString();
	}
	
	private void writeFile(File file, String data) throws Exception {
		PrintWriter br = new PrintWriter(new FileOutputStream(file));
		br.write(data);
		br.flush();
		br.close();
	}
}
