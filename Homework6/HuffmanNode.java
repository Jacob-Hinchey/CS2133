import java.io.*;
import java.util.*;

public class HuffmanNode implements Comparable, Serializable{

	private HuffmanNode huffNodeUsed;
	private HuffmanNode zero;
	private HuffmanNode one;
	public int frequency;
	public byte bts;
	public HuffmanNode(byte bts, int frequencey) {
		this.bts = bts;
		frequency = 0;
	}

	public HuffmanNode(HuffmanNode huffNodeUsed, HuffmanNode zero, HuffmanNode one, int frequency) {
		this.huffNodeUsed = huffNodeUsed;
		this.zero = zero;
		this.one = one;
		this.frequency = frequency;
	}

	public HuffmanNode getNodeUsed() {
		return huffNodeUsed;
	}

	public void setNodeUsed(HuffmanNode huffNodeUsed) {
		this.huffNodeUsed = huffNodeUsed;
	}

	public HuffmanNode getZero() {
		return zero;
	}

	public HuffmanNode getOne() {
		return one;
	}

	public byte getBts() {
		return bts;
	}

	public void increaseFrequency() {
		frequency++;
	}

	public String getBits() {
		HuffmanNode hold = getNodeUsed();
		String rtrn = "";
		if(hold == null){
			return rtrn;
		}
		else if(this == hold.getZero()){
			rtrn += "0";
		}
		else if(this == hold.getOne()){
			rtrn += "1";
		}
		return rtrn = hold.getBits() + rtrn;
	}
	
	@Override
	public int compareTo(Object obj) {
		HuffmanNode hold = (HuffmanNode)obj;
		if(frequency < hold.frequency){
			return -1;
		}
		else if(frequency > hold.frequency){
			return 1;
		}
		return 0;
	}
}
