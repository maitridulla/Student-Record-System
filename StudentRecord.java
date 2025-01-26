
import java.io.*;
import java.util.Scanner;

class Student {
	protected String name, department, password, username, phno, read;
	protected int JPR, MIC, SEN, GAD, DCC;
	protected String useradmin = "Admin@3009";
	protected String passadmin = "admin@3009";
	protected String filename = "Student.txt";
	protected int user_no, roll_no;
	protected boolean found = false;
	Scanner Sc = new Scanner(System.in);

	public void getstud() throws IOException {
		File file = new File("Student.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		RandomAccessFile rf = new RandomAccessFile(file, "rw");

		Sc.nextLine();
		System.out.println("\n Enter Full Name: ");
		name = Sc.nextLine();
		System.out.println(" Enter Roll No: ");
		roll_no = Sc.nextInt();
		System.out.println(" Enter Phone Number: ");// string
		Sc.nextLine();
		phno = Sc.nextLine();
		System.out.println(" Enter Department: ");
		department = Sc.nextLine();

		rf.seek(0);
		while (rf.getFilePointer() < rf.length()) {
			read = rf.readLine();
			String[] linesplit = read.split("!");
			if (roll_no == Integer.parseInt(linesplit[1])) {
				found = true;
				break;
			}
		}
		if (found == false) {
			read = name + "!" + String.valueOf(roll_no) + "!" + phno + "!" + department + "!" + "0" + "!" + "0" + "!"
					+ "0" + "!" + "0";
			rf.writeBytes(read);
			rf.writeBytes(System.lineSeparator());
			System.out.println("\n Record Created");
			rf.close();
		}
	}

	public boolean Loginadmin() {
		boolean c1, c2;
		System.out.println("\n Enter Username: ");
		username = Sc.next();
		System.out.println(" Enter Password: ");
		password = Sc.next();
		c1 = passadmin.equals(password);
		c2 = useradmin.equals(username);
		if (c1 == true && c2 == true)
			return true;
		else
			System.out.println("\n\n Invalid Username/Password!!! ");
		System.out.println("\n Please Check It....");
		return false;
	}

	public void delete() throws IOException {
		found = false;
		int j;
		RandomAccessFile rf = new RandomAccessFile(filename, "rw");
		System.out.println("\n Enter Roll Number: ");
		user_no = Sc.nextInt();
		while (rf.getFilePointer() < rf.length()) {
			read = rf.readLine();
			String linesplit[] = read.split("!");
			if (user_no == Integer.parseInt(linesplit[1])) {
				found = true;
				break;
			}
		}

		if (found == true) {
			File tmpFile = new File("Temp.txt");
			RandomAccessFile tmprf = new RandomAccessFile(tmpFile, "rw");
			rf.seek(0);
			while (rf.getFilePointer() < rf.length()) {
				read = rf.readLine();
				String linesplit[] = read.split("!");
				if (user_no == Integer.parseInt(linesplit[1])) {
					continue;
				}
				tmprf.writeBytes(read);
				tmprf.writeBytes(System.lineSeparator());
			}
			rf.seek(0);
			tmprf.seek(0);
			while (tmprf.getFilePointer() < tmprf.length()) {
				rf.writeBytes(tmprf.readLine());
				rf.writeBytes(System.lineSeparator());
			}
			rf.setLength(tmprf.length());

			tmprf.close();
			rf.close();

			tmpFile.delete();
			System.out.println("\n Record Sucessfully Deleted...");
		} else {
			System.out.println("\n Record Not Found....");
		}
	}

	public void get_marks() throws IOException {
		RandomAccessFile rf = new RandomAccessFile(filename, "rw");
		System.out.println("\n Enter Roll of Student: ");
		user_no = Sc.nextInt();
		rf.seek(0);
		while (rf.getFilePointer() < rf.length()) {
			read = rf.readLine();
			String[] linesplit = read.split("!");
			if (user_no == Integer.parseInt(linesplit[1])) {
				found = true;
				break;
			}
		}
		if (found == true) {
			File tmpFile = new File("Temp.txt");
			RandomAccessFile tmprf = new RandomAccessFile(tmpFile, "rw");
			System.out.println("\n Enter Marks: ");
			System.out.println("\n JPR: ");
			JPR = Sc.nextInt();
			System.out.println(" MIC: ");
			MIC = Sc.nextInt();
			System.out.println(" SEN: ");
			SEN = Sc.nextInt();
			System.out.println(" DCC: ");
			DCC = Sc.nextInt();
			rf.seek(0);
			while (rf.getFilePointer() < rf.length()) {
				read = rf.readLine();
				String[] linesplit = read.split("!");
				if (user_no == Integer.parseInt(linesplit[1])) {
					read = linesplit[0] + "!" + linesplit[1] + "!" + linesplit[2] + "!" + linesplit[3] + "!"
							+ String.valueOf(JPR) + "!" + String.valueOf(MIC) + "!" + String.valueOf(SEN) + "!"
							+ String.valueOf(DCC);
				}
				tmprf.writeBytes(read);
				tmprf.writeBytes(System.lineSeparator());
			}
			rf.seek(0);
			tmprf.seek(0);
			while (tmprf.getFilePointer() < tmprf.length()) {
				rf.writeBytes(tmprf.readLine());
				rf.writeBytes(System.lineSeparator());
			}
			rf.setLength(tmprf.length());

			tmprf.close();
			rf.close();

			tmpFile.delete();
		}
		rf.close();
	}

	public void update() throws IOException {
		// found=false;
		RandomAccessFile rf = new RandomAccessFile(filename, "rw");
		System.out.println("\n Enter Roll of Student: ");
		user_no = Sc.nextInt();
		rf.seek(0);
		while (rf.getFilePointer() < rf.length()) {
			read = rf.readLine();
			String[] linesplit = read.split("!");
			if (user_no == Integer.parseInt(linesplit[1])) {
				found = true;
				break;
			}
		}
		if (found == true) {
			String mno, dept;
			char c, c1;
			File tmpFile = new File("Temp.txt");
			RandomAccessFile tmprf = new RandomAccessFile(tmpFile, "rw");
			System.out.println("\n Do you want to update Mob No (Y/N): ");
			c1 = Sc.next().charAt(0);
			System.out.println("\n Do you want to update Department (Y/N): ");
			c = Sc.next().charAt(0);

			rf.seek(0);
			while (rf.getFilePointer() < rf.length()) {
				read = rf.readLine();
				String[] linesplit = read.split("!");
				if (user_no == Integer.parseInt(linesplit[1])) {
					if (c == 'y' | c == 'Y') {
						System.out.println(" Enter New Department: ");
						Sc.nextLine();
						dept = Sc.nextLine();
						read = linesplit[0] + "!" + linesplit[1] + "!" + linesplit[2] + "!" + dept + "!" + linesplit[4]
								+ "!" + linesplit[5] + "!" + "!" + linesplit[6] + "!" + linesplit[7];
					}
					if (c1 == 'y' | c1 == 'Y') {
						System.out.println(" Enter New Mobile Number: ");
						Sc.nextLine();
						mno = Sc.nextLine();

						read = linesplit[0] + "!" + linesplit[1] + "!" + mno + "!" + linesplit[3] + "!" + linesplit[4]
								+ "!" + linesplit[5] + "!" + "!" + linesplit[6] + "!" + linesplit[7];
					}
				}
				tmprf.writeBytes(read);
				tmprf.writeBytes(System.lineSeparator());
			}
			rf.seek(0);
			tmprf.seek(0);
			while (tmprf.getFilePointer() < tmprf.length()) {
				rf.writeBytes(tmprf.readLine());
				rf.writeBytes(System.lineSeparator());
			}
			rf.setLength(tmprf.length());

			tmprf.close();
			rf.close();

			tmpFile.delete();
		}
		rf.close();
	}

	public void display_record(String n, int no, String p, String d) {
		System.out.println(n + "  " + no + "  " + p + "  " + d);
	}

	public void display_marks(String n, int j, int m, int s, int d) {
		System.out.println(n + "  " + j + "  " + m + "  " + s + "  " + d);
	}

	public void view_record() throws IOException {
		System.out.println("\n Enter Roll No: ");
		user_no = Sc.nextInt();
		RandomAccessFile rf = new RandomAccessFile(filename, "rw");
		System.out.println("\nName     no     Phno     dept");
		while (rf.getFilePointer() < rf.length()) {
			read = rf.readLine();
			String linesplit[] = read.split("!");
			name = linesplit[0];
			roll_no = Integer.parseInt(linesplit[1]);
			phno = linesplit[2];
			department = linesplit[3];
			if (user_no == roll_no) {
				display_record(name, roll_no, phno, department);
				break;
			}
		}
	}

	public void view_marks() throws IOException {
		System.out.println("\n Enter Roll No: ");
		user_no = Sc.nextInt();
		RandomAccessFile rf = new RandomAccessFile(filename, "rw");
		System.out.println("\nName     JPR     MIC     SEN     DCC");
		while (rf.getFilePointer() < rf.length()) {
			read = rf.readLine();
			String linesplit[] = read.split("!");
			if (user_no == Integer.parseInt(linesplit[1])) {
				display_marks(linesplit[0], Integer.parseInt(linesplit[4]), Integer.parseInt(linesplit[5]),
						Integer.parseInt(linesplit[6]), Integer.parseInt(linesplit[7]));
				break;
			}
		}
	}

	public void view_all() throws IOException {
		RandomAccessFile rf = new RandomAccessFile(filename, "rw");
		System.out.println("\nName     no     Phno     dept");
		while (rf.getFilePointer() < rf.length()) {
			read = rf.readLine();
			String linesplit[] = read.split("!");
			name = linesplit[0];
			roll_no = Integer.parseInt(linesplit[1]);
			phno = linesplit[2];
			department = linesplit[3];
			display_record(name, roll_no, phno, department);
		}
	}

}

public class StudentRecord {

	static Student S = new Student();

	static Scanner Sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		int ch, ch1, ch2, ch3;
		boolean b;
		do {
			System.out.println("\n\t\t\t\t=============================");
			System.out.println("\t\t\t\t=     *  MAIN MENU  *       =");
			System.out.println("\t\t\t\t=                           =");
			System.out.println("\t\t\t\t=     1: ADMIN              =");
			System.out.println("\t\t\t\t=     2: STUDENT            =");
			System.out.println("\t\t\t\t=     3: EXIT               =");
			System.out.println("\t\t\t\t=                           =");
			System.out.println("\t\t\t\t=============================");
			System.out.println("Enter Your Choice: ");
			ch = Sc.nextInt();
			switch (ch) {
			case 1:
				b = S.Loginadmin();
				if (b == true) {
					do {
						System.out.println("\n\t\t\t\t================================");
						System.out.println("\t\t\t\t=     *  ADMIN MODULE  *       =");
						System.out.println("\t\t\t\t=                              =");
						System.out.println("\t\t\t\t=     1: INSERT RECORD         =");
						System.out.println("\t\t\t\t=     2: UPDATE RECORD         =");
						System.out.println("\t\t\t\t=     3: DELETE RECORD         =");
						System.out.println("\t\t\t\t=     4: VIEW RECORD           =");
						System.out.println("\t\t\t\t=     5: EXIT                  =");
						System.out.println("\t\t\t\t=                              =");
						System.out.println("\t\t\t\t================================");
						System.out.println("\n\n\t\t\t\t Enter Your Choice: ");
						ch1 = Sc.nextInt();
						switch (ch1) {
						case 1:
							do {
								System.out.println("\n\t\t\t\t=================================");
								System.out.println("\t\t\t\t=     *  INSERT MODULE  *        =");
								System.out.println("\t\t\t\t=                                =");
								System.out.println("\t\t\t\t=     1: INSERT STUDENT          =");
								System.out.println("\t\t\t\t=     2: INSERT MARKS            =");
								System.out.println("\t\t\t\t=     3: EXIT                    =");
								System.out.println("\t\t\t\t=                                =");
								System.out.println("\t\t\t\t==================================");
								System.out.println("\n\n\t\t\t\t Enter Your Choice: ");
								ch2 = Sc.nextInt();
								switch (ch2) {
								case 1:
									S.getstud();
									break;
								case 2:
									S.get_marks();
									break;
								case 3:
									break;
								}
							} while (ch2 != 3);
							break;
						case 2:
							S.update();
							break;
						case 3:
							S.delete();
							break;
						case 4:
							S.view_all();
							break;
						case 5:
							break;
						}
					} while (ch1 != 5);
				}
				break;
			case 2:
				do {
					System.out.println("\n\t\t\t\t================================");
					System.out.println("\t\t\t\t=     *  STUDENT MODULE  *     =");
					System.out.println("\t\t\t\t=                              =");
					System.out.println("\t\t\t\t=     1: VIEW MARKS            =");
					System.out.println("\t\t\t\t=     2: VIEW RECORD           =");
					System.out.println("\t\t\t\t=     3: EXIT                  =");
					System.out.println("\t\t\t\t=                              =");
					System.out.println("\t\t\t\t================================");
					System.out.println("\n\t\t\t\t Enter Your Choice: ");
					ch3 = Sc.nextInt();
					switch (ch3) {
					case 1:
						S.view_marks();
						break;
					case 2:
						S.view_record();
						break;
					case 3:
						break;
					}
				} while (ch3 != 3);
			}
		} while (ch != 3);
	}
}
