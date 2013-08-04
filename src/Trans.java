import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;


public class Trans {
	public static void main(String[] args) throws Exception{
		//存放单位-到m的换算因子
		HashMap<String, Float> map = new HashMap<String, Float>();
		//读input.txt文件
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String line = "";
		//新建output.txt文件
		File out = new File("output.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(out));
		bw.write("rqzhang1990@gmail.com\r\n");
		bw.write("\r\n");
		int index = 0;
		while ((line = br.readLine()) != null) {
			index++;
			String[] length = line.split(" ");
			if (index<=6) {
				map.put(length[1], Float.parseFloat(length[3]));
				//bw.write(length[1]+"\r\n");
			}
			if (index>6) {
				double sum=0.0;
				for (int i = 0; i < length.length; i++) {
					if (length[i].equals("miles")) {
						length[i]="mile";
					}
					else if (length[i].equals("yards")) {
						length[i]="yard";
					}
					else if (length[i].equals("inches")) {
						length[i]="inch";
					}
					else if (length[i].equals("feet")) {
						length[i]="foot";
					}
					else if (length[i].equals("faths")) {
						length[i]="fath";
					}
					else if (length[i].equals("furlongs")) {
						length[i]="furlong";
					}
					if (i==1) {
						if (map.containsKey(length[i])) {
							sum = Float.parseFloat(length[i-1])*map.get(length[i]);
						}
					}
					else if (i>1&&i<length.length) {
						if (map.containsKey(length[i])) {
						if (length[i-2].equals("+")) {
						sum += Float.parseFloat(length[i-1])*map.get(length[i]);
								}else if (length[i-2].equals("-")) {
									sum =sum - Float.parseFloat(length[i-1])*map.get(length[i]);
								}
							}	
					}
					}
					bw.write(String.format("%.2f", sum) + " m\r\n");
				}
								
			}
		br.close();
		bw.close();
		}	
	}
