import java.util.*;
public class OnlineBanking{
public static float check;
public static void deposite(float n)
{
	check=check+n;
	System.out.println("Credited to Rs."+n+"/-");
	System.out.println("Total Balance available:"+check+"/-");
}
public static void withdrawal(float n)
{
	if(check<n)
	{
	System.out.println("Insufficient Balance!!!");
	}
	else
	{
	check=check-n;
	System.out.println("Debited from Rs."+n+"/-");
	System.out.println("Total Balance available:"+check+"/-");
	}
}
public static void check()
{
		System.out.println("Bank Balance fetched Successfully!");
		System.out.println("Rs."+check+"/-");
}
public static void main(String args[])
{
	Scanner sc=new Scanner(System.in);
	System.out.println("Banking Operations:");
	System.out.println("1.Deposite");
	System.out.println("2.Withdrawal");
	System.out.println("3.Check Balance");
	System.out.println("4.Exit");
	while(true){
	int n=sc.nextInt();
	switch(n){
		case 1:
			System.out.println("Enter the Amount to be Deposite:");
			float d=sc.nextFloat();
			 deposite(d);
		break;
		case 2:
			System.out.println("Enter the Amount to be Withdraw:");
			float w=sc.nextFloat();
			withdrawal(w);
		break;
		case 3:
			check();
		break;
		case 4:
			System.out.println("Thank You!!!");
			System.exit(0);
		break;
		default:
			System.out.println("Invalid Choice!!!");

	}
	}

	
}
}