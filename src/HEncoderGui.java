// Contributed by Shivang Agarwal

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.BitSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//Paths to file.
// src/text/text.txt
// src/text.txt

@SuppressWarnings("serial")
public class HEncoderGui extends JFrame implements ActionListener {
	HEncoder he = new HEncoder(
			"Real World Data - 16. De Quincey, the essayist, once said that the German sentence is like a carryall - always room for one more. That used to be true of the English sentence. Originally, to be sure, our sentence was short, but under the influence of Latin studies it grew heavy and unwieldy. From sixteenth century writers it is possible to quote sentences of five or six hundred words. Such a sentence would fill two pages of this book.When newspapers came to the front, the English sentence began to drop a part of its words. Yet one of the best journalists of the eighteenth century, Daniel Defoe, who wrote Robinson Crusoe, is not above writing an occasional sentence of great length. Here is a business sentence from Defoe:One office for lone of money for customs of goods, which by a plain method might be so ordered that the merchant might with ease pay the highest customs down, and so, by allowing the bank four per cent advance, be first to secure the �10 per cent which the king allows for prompt payment at the custom house, and be also freed from the troublesome work of finding bondsmen and securities for the money - which has exposed many a man to the tyranny of extents, either for himself or his friend, to his utter ruin, who under a more moderate prosecution had been able to pay all his debts, and by this method has been torn to pieces and disabled from making any tolerable proposal to his creditors.Here are a hundred and twenty-nine words in one sentence. The book from which it is taken, An Essay upon Projects, averages more than sixty words to the sentence. How long is the average sentence today! It depends on the man, but in even the most literary prose it will not average more than thirty words. The average sentence of Macaulays England is 23.43. Emersons average sentence is less than that.But do business men never write long sentences! Alas! many are only too prone to this form of amusement. Amusement it is, for there is a curious pleasure in seeing how many words may be packed into one package. In Dean van Benthuy-sens excellent brochure on English in Commercial Correspond* ence - published by the LaSalle Extension University - the following is quoted:I am in receipt of your letter of the 9th instant, relating in part to the stenographer and type-writer examinations next spring and also the question of local appointments in connection with the conducting of Civil Service examinations, concerning the latter of which I would say that with the exception of the route examinations which are conducted by the various district secretaries, the examinations are held by employees of the post offices at the different places of examination, who have been specially designated for such purpose under a provision of the Civil Service rules.The youth who got that must have felt as if he were perusing a railroad time-table. Good mental exercise! Never, never use that argument. To cause your reader or correspondent unnecessary mental labor is the greatest of all blunders in business English. The more patience he spends in getting at your thought, the less he will have for your proposition. Let us turn that alleged sentence into a paragraph. There are several versions that might be made. Here is one. [Note that while the indention, or blank space at the beginning of the first line, is a mere trifle in the printed line, it should be at least an inch deep in written manuscript.] -I have your inquiry of June ninth. You ask first about the stenographer and type-writer examinations next spring. [Here let him answer that inquiry.] You inquire also as to local appointments in connection with the conducting of Civil Service examinations. The route examinations are conducted by the various district secretaries. The others are held at the different places of examination by post-office employees who have been specially designated for such purpose under the Civil Service rules.The single sentence has ninety-four words; the corresponding paragraph has only seventy, although it contains five sentences. Yet if the paragraph isnt easier to grasp than the sentence - well, our theory is all wrong.The paragraph gives the writer room. It allows him to take breath. He can proceed in a leisurely manner to make one point and then another. And precisely as these are advantages to the writer, they are advantages to the reader.� 17. Another thing. This great modern invention, the paragraph, permits the writer to emphasize the important thought. Suppose that the paragraph is to deal with a group of details which are all of the same sort, but one of which is the most important. He can run a group of details together in one sentence, using semicolons if necessary, and save a short strong sentence for the one detail that deserves itNote how the emphasis is distributed in the following excellent paragraph:There is always one by which the rest are measured In the magazine world, that one has always been and is today THE CENTURY. Ask writers where their best productions are first offered; ask editors which magazine they would rather conduct; ask public men where articles carry most influence; ask artists where they would prefer to be represented; ask the public what magazine is the first choice among people of real influence, and the answer to each question is the same: THE CENTURY.� 18. In the business English of our time the paragraph tends to be short. This is due to the influence of advertising. That white space before and after a paragraph calls attention to the text and relieves the tired eye from attempting too much at once. So arises what we call the single-sentence paragraph. You find it in trade-journals, and in the editorials of a certain class of newspapers. Here is a specimen, written by the well-known advertising agent, Mr. John Kennedy:More than six years ago I had the good fortune to prepare a series of advertisements for a very able Advertiser in the west");
	private JLabel lblPath;
	private JTextField pathField;
	private JButton btncompress;
	private JButton btndecompress;

	public HEncoderGui() {
		GridLayout layout = new GridLayout(2, 2);
		super.setLayout(layout);

		Font font = new Font("Comic Sans MS", 1, 80);
		Font fontsmall = new Font("Comic Sans MS", 1, 20);

		lblPath = new JLabel("File Path: ");
		lblPath.setFont(font);
		super.add(lblPath);

		pathField = new JTextField();
		pathField.setFont(fontsmall);
		super.add(pathField);

		btncompress = new JButton("Compress");
		btncompress.setFont(font);
		btncompress.addActionListener(this);
		super.add(btncompress);

		btndecompress = new JButton("Decompress");
		btndecompress.setFont(font);
		btndecompress.addActionListener(this);
		super.add(btndecompress);

		super.setTitle("Text File Compressor and Decompressor");
		super.setExtendedState(MAXIMIZED_BOTH);
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setVisible(true);
		setAtStartup();
	}

	private void setAtStartup() {
		pathField.setText("");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btncompress) {
			try {
				handleCompress();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == btndecompress) {
			try {
				handleDecompress();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	@SuppressWarnings("unused")
	private void handleCompress() throws IOException {
		StringBuilder path = new StringBuilder(pathField.getText());

		if (!path.substring(path.length() - 3, path.length()).equals("txt")) {
			JOptionPane.showMessageDialog(this, "Please choose correct file format.");
			return;
		}

		// open a text file
		FileReader input = null;
		try {
			input = new FileReader(path.toString());

			int c;
			String str = "";

			// read all char of text file in a string to be converted
			while ((c = input.read()) != -1) {
				str += (char) c;
			}

			input.close();
			String encoded = he.compress(str);

			// convert the char string to bits
			BitSet bitSet = new BitSet(encoded.length());
			int bitcounter = 0;
			for (Character ch : encoded.toCharArray()) {
				if (ch.equals('1')) {
					bitSet.set(bitcounter);
				}
				bitcounter++;
			}

			// delete the text file as .bin file is created.
			boolean deltxtFile = new File(path.toString()).delete();

			// creating path to compressed file.
			StringBuilder Npath = new StringBuilder(path.substring(0, path.length() - 4));
			Npath.append(".sag");

			// save the bits to file
			byte[] byt = bitSet.toByteArray();

			FileOutputStream fos = new FileOutputStream(Npath.toString());
			fos.write(byt);
			fos.close();
			JOptionPane.showMessageDialog(this, "Your file is compressed");

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "File path does not exist. Please recheck the path.");
		}
	}

	@SuppressWarnings("unused")
	private void handleDecompress() throws IOException {
		StringBuilder path = new StringBuilder(pathField.getText());

		if (!path.substring(path.length() - 3, path.length()).equals("sag")) {
			JOptionPane.showMessageDialog(this, "Please choose correct file format.");
			return;
		}

		// read data from encoded.bin
		FileInputStream fin;
		try {
			fin = new FileInputStream(path.toString());

			// Just to find length of string inside file.
			int s = 0;
			Integer val = 0;
			while ((val = fin.read()) != -1) {
				s++;
			}
			fin.close();

			// saving the read data from bin file to byte array in byt.
			byte[] byt = new byte[s];
			FileInputStream finn = new FileInputStream(path.toString());
			finn.read(byt);
			finn.close();

			// converting the byt array to a string to decompress
			BitSet set = BitSet.valueOf(byt);
			String binaryString = "";
			for (int i = 0; i <= set.length(); i++) {
				if (set.get(i)) {
					binaryString += "1";
				} else {
					binaryString += "0";
				}
			}

			// creating a new path to Decoding file
			StringBuilder Npath = new StringBuilder(path.substring(0, path.length() - 4));
			Npath.append(".txt");
			String decoded = he.decompress(binaryString);
			// Decoding the file
			PrintWriter writer = new PrintWriter(Npath.toString(), "UTF-8");
			writer.println(decoded);
			writer.close();

			// delete .bin file as .text file is created again.
			boolean delbinFile = new File(path.toString()).delete();
			JOptionPane.showMessageDialog(this, "Your file is Decompressed");

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "File path does not exist. Please recheck the path.");
		}
	}
}
