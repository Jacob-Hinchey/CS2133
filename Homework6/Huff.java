import java.io.*;
import java.util.*;

public class Huff{

	public static void main(String[] args){
		if(args.length == 0){
			usage();
			System.exit(0);
		}
		try{
			FileInputStream inputFile = new FileInputStream(args[0]);
			int fileLng = inputFile.available();
			byte[] bts = new byte[fileLng];
			String file = args[0];
			inputFile.read(bts);
			inputFile.close();
			HashMap<Byte, HuffmanNode> nodeHash = new HashMap<Byte, HuffmanNode>();
			int count = 256;
			//Must start at -128 for byte size
			for(byte i = -128; count > 0 ; i++){
				HuffmanNode huffNode = new HuffmanNode(i, 0);
				nodeHash.put(i, huffNode);
				count--;
			}
			for(int i = 0; i < bts.length; i++){
				nodeHash.get(bts[i]).increaseFrequency();
			}
			count = 256;
			PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<HuffmanNode>();
			for(byte i = -128; count > 0; i++){
				if(nodeHash.get(i).frequency != 0){
					priorityQueue.add(nodeHash.get(i));
				}
				count--;
			}
			while(true){
				if(priorityQueue.size() == 1){
					break;
				}
				else{
					HuffmanNode zero = priorityQueue.poll();
					HuffmanNode one = priorityQueue.poll();
					HuffmanNode orig = new HuffmanNode(null, zero, one, (zero.frequency + one.frequency));
					zero.setNodeUsed(orig);
					one.setNodeUsed(orig);
					priorityQueue.add(orig);
				}
			}
			HuffmanNode treeNode = priorityQueue.poll();
			String bits = "";
			for(int i = 0; i < bts.length; i ++){
				bits = bits + nodeHash.get(bts[i]).getBits();
			}
			int padding = (8 - (bits.length() % 8));
			for(int i = 0; i < padding; i++){
				bits = bits + "0";
			}
			ArrayList<Integer> bitArr = new ArrayList<Integer>();
			for(int i = 0; i < bits.length(); i++){
				String bitStr = bits.substring(i, i + 1);
				bitArr.add(Integer.parseInt(bitStr));
			}
			byte[] codedBytes = Twiddle.bitsToBytes(bitArr);
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file + ".huff"));
			outputStream.writeObject(treeNode);
			outputStream.writeInt(fileLng);
			outputStream.writeInt(codedBytes.length);
			outputStream.write(codedBytes);
			outputStream.flush();
			outputStream.close();
		}
		catch(IOException e){
			System.out.println("The file could not be found!");
		}
	}
	public static void usage(){
		System.out.println("USAGE java Huff inFile");
	}
}
