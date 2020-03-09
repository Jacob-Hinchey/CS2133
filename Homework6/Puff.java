import java.io.*;
import java.util.*;

public class Puff {
	static HuffmanNode treeNode;
	static List<Integer> bits;
	static int count = 0;
	public static void main(String[] args){
		try{
			String file = args[0];
			if(!file.contains(".huff")){
				usage();
				System.exit(0);
			}
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
			treeNode = (HuffmanNode)inputStream.readObject();
			int fileLng = inputStream.readInt();
			int btsLength = inputStream.readInt();
			byte[] byteCode = new byte[btsLength];
			inputStream.readFully(byteCode);
			inputStream.close();
			bits = new ArrayList<Integer>();
			bits = Twiddle.bytesToBits(byteCode);
			byte[] byteDecyper = new byte[fileLng];
			for(int i = 0; i < fileLng; i++){
				byteDecyper[i] = getByteDecyp(treeNode);
			}
			FileOutputStream outputStream = new FileOutputStream(file.replace(".huff", ""));
			outputStream.write(byteDecyper);
			outputStream.flush();
			outputStream.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	public static byte getByteDecyp(HuffmanNode huffNodeUse) {
		HuffmanNode a = huffNodeUse.getZero();
		HuffmanNode b = huffNodeUse.getOne();
		byte rtrn = 0;
		if(a == null && b == null) {
			return huffNodeUse.getBts();
		}
		else if (bits.get(count) == 0) {
			count++;
			rtrn = getByteDecyp(a);
		}
		else if (bits.get(count) == 1) {
			count++;
			rtrn = getByteDecyp(b);
		}
		return rtrn;
	}

	public static void usage() {
		System.out.println("USAGE java Puff inFile.huff");
	}
}
