package com.krzem.pi_calculation_bbp;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.Exception;



public class Main{
	public static void main(String[] args){
		new Main();
	}



	private boolean _file=false;



	public Main(){
		System.out.println(this.pi("3141",null,null));
	}



	private String pi(String m,String t,String i){
		m=this._add(m,"3");
		if (t==null){
			t="0";
			i="0";
		}
		else{
			i=this._add(i,"1");
		}
		for (;this._smaller(i,m);i=this._add(i,"1")){
			String[] _fr=this._sub_fr("4",this._add(this._mult(i,"8"),"1"),"2",this._add(this._mult(i,"8"),"4"));
			_fr=this._sub_fr(_fr[0],_fr[1],"1",this._add(this._mult(i,"8"),"5"));
			_fr=this._sub_fr(_fr[0],_fr[1],"1",this._add(this._mult(i,"8"),"6"));
			String n=_fr[0];
			String dn=this._mult(_fr[1],this._pow("16",i));
			String fr=this._div_fr(n,dn,m);
			t=this._add_dec_fr(t,fr);
			this._dump(i,t);
			System.out.printf("Step #%s:\t%s / %s\n\n",i,n,dn);
		}
		this._dump(this._sub(m,"3"),this._substr(t,this._sub(m,"3")));
		return this._substr(t,this._sub(m,"3"));
	}



	private String[] _add_fr(String n1,String dn1,String n2,String dn2){
		return new String[]{this._add(this._mult(n1,dn2),this._mult(n2,dn1)),this._mult(dn1,dn2)};
	}



	private String[] _sub_fr(String n1,String dn1,String n2,String dn2){
		return new String[]{this._sub(this._mult(n1,dn2),this._mult(n2,dn1)),this._mult(dn1,dn2)};
	}



	private String _div_fr(String a,String b,String c){
		a+=".";
		for (String i="0";this._smaller(i,c);i=this._add(i,"1")){
			a+="0";
		}
		String o="";
		String dv="";
		for (int i=0;i<a.length();i++){
			if (a.charAt(i)=='.'){
				o+=".";
			}
			else{
				dv+=String.valueOf(a.charAt(i));
				if (this._smaller(dv,b)==true){
					o+="0";
				}
				else{
					String dn="0";
					while (!this._smaller(dv,b)){
						dv=this._sub(dv,b);
						dn=this._add(dn,"1");
					}
					o+=dn;
				}
			}
		}
		while (o.length()>=2&&o.charAt(0)=='0'){
			o=o.substring(1);
		}
		return o;
	}



	private boolean _is_zero(String n){
		return n.equals("0");
	}



	private boolean _smaller(String a,String b){
		if (b.equals("infinity")){
			return true;
		}
		while (a.length()>=2&&a.charAt(0)=='0'){
			a=a.substring(1);
		}
		while (b.length()>=2&&b.charAt(0)=='0'){
			b=b.substring(1);
		}
		if (a.length()<b.length()){
			return true;
		}
		if (a.length()>b.length()){
			return false;
		}
		for (int i=0;i<a.length();i++){
			int na=Integer.parseInt(String.valueOf(a.charAt(i)));
			int nb=Integer.parseInt(String.valueOf(b.charAt(i)));
			if (na<nb){
				return true;
			}
			if (na>nb){
				return false;
			}
		}
		return false;
	}



	private String _modulo(String a,String b){
		return this._sub(a,this._mult(this._div(a,b),b));
	}



	private String _div(String a,String b){
		String o="";
		String dv="";
		for (int i=0;i<a.length();i++){
			dv+=String.valueOf(a.charAt(i));
			if (this._smaller(dv,b)==true){
				o+="0";
			}
			else{
				String dn="0";
				while (!this._smaller(dv,b)){
					dv=this._sub(dv,b);
					dn=this._add(dn,"1");
				}
				o+=dn;
			}
		}
		while (o.length()>=2&&o.charAt(0)=='0'){
			o=o.substring(1);
		}
		return o;
	}



	private String _sub(String a,String b){
		String o="";
		while (a.length()<b.length()){
			a="0"+a;
		}
		while (a.length()>b.length()){
			b="0"+b;
		}
		int s=0;
		for (int i=a.length()-1;i>=0;i--){
			int na=Integer.parseInt(String.valueOf(a.charAt(i)))-s;
			int nb=Integer.parseInt(String.valueOf(b.charAt(i)));
			s=0;
			if (nb>na){
				na+=10;
				s=1;
			}
			o=Integer.toString(na-nb)+o;
		}
		while (o.length()>=2&&o.charAt(0)=='0'){
			o=o.substring(1);
		}
		return o;
	}



	private String _mult(String a,String b){
		String to="0";
		for (int mi=b.length()-1;mi>=0;mi--){
			int mt=Integer.parseInt(String.valueOf(b.charAt(mi)));
			String o="";
			int p=0;
			for (int i=a.length()-1;i>=0;i--){
				int n=Integer.parseInt(String.valueOf(a.charAt(i)));
				o=Integer.toString((n*mt+p)%10)+o;
				p=(n*mt+p)/10;
			}
			if (p>0){
				o=Integer.toString(p)+o;
			}
			for (int i=0;i<b.length()-1-mi;i++){
				o+="0";
			}
			to=this._add(to,o);
		}
		return to;
	}



	private String _pow(String a,String b){
		String t="1";
		for (String i="0";this._smaller(i,b);i=this._add(i,"1")){
			t=this._mult(t,a);
		}
		return t;
	}



	private String _add(String a,String b){
		String o="";
		while (a.length()<b.length()){
			a="0"+a;
		}
		while (a.length()>b.length()){
			b="0"+b;
		}
		int p=0;
		for (int i=a.length()-1;i>=0;i--){
			int na=Integer.parseInt(String.valueOf(a.charAt(i)));
			int nb=Integer.parseInt(String.valueOf(b.charAt(i)));
			o=Integer.toString((na+nb+p)%10)+o;
			p=(na+nb+p)/10;
		}
		if (p>0){
			o=Integer.toString(p)+o;
		}
		return o;
	}



	private String _add_dec_fr(String a,String b){
		String o="";
		if (a.indexOf(".")==-1){
			a+=".";
		}
		if (b.charAt(0)=='.'){
			b="0"+b;
		}
		while (a.length()<b.length()){
			a+="0";
		}
		while (a.length()>b.length()){
			b+="0";
		}
		int p=0;
		for (int i=a.length()-1;i>=0;i--){
			if (a.charAt(i)=='.'){
				o="."+o;
			}
			else{
				int na=Integer.parseInt(String.valueOf(a.charAt(i)));
				int nb=Integer.parseInt(String.valueOf(b.charAt(i)));
				o=Integer.toString((na+nb+p)%10)+o;
				p=(na+nb+p)/10;
			}
		}
		if (p>0){
			o=Integer.toString(p)+o;
		}
		return o;
	}



	private String _substr(String s,String l){
		return s.substring(0,Integer.parseInt(l));
	}



	private void _dump(String i,String pi){
		if (this._file==true){
			return;
		}
		this._file=true;
		Main cls=this;
		new Thread(new Runnable(){
			@Override
			public void run(){
				try{
					BufferedWriter w=new BufferedWriter(new FileWriter("dump.txt"));
					w.write(String.format("%s\n\n\n%s",i,pi));
					w.close();
					cls._file=false;
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}
		}).start();
	}
}
