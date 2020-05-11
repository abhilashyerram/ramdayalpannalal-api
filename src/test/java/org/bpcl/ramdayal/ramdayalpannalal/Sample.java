package org.bpcl.ramdayal.ramdayalpannalal;

class Sample {

    public static void main (String[] args) {
        String[] entries = new String[4];
        entries[0] = "1a7"; //8
        entries[1] = "2a5c";  //7
        entries[2] = "1a7aa3";
        entries[3] = "2a5c3b4";

        System.out.println( SumOfTheDigits(entries) ); //40
    }

    public static Integer SumOfTheDigits(String[] entries)
    {
        int sum = 0;

        for(String entry: entries){

                for (int i=0; i<entry.length(); i++){

                        char a = entry.charAt(i);
                        if(Character.isDigit(a)){
                            sum = sum + Integer.parseInt(String.valueOf(a));
                        }
                }

        }
        return sum;
    }


}




