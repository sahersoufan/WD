package sample.proccess;



public class Repair {
    private static String MainDomain;

    public static String RepairDomain(String MainUrl){
        String Split[]=MainUrl.split("/");
        String ans=null;
        ans=MainUrl;
        if(Split.length>0){
            if(!Split[0].equals("https:")) {
                if (!Split[0].equals("http:")) {
                   return ans= "https://"+MainUrl;
                }
            }
        }
        String Split1[]=ans.split("/");
        MainDomain=Split1[0]+"//"+Split1[2];


       return MainUrl;




    }
    public static String RepairUrl( String RepairLink)  {


        String Split[]=RepairLink.split("/");

        String Split2[]=RepairLink.split("\\.");
        String NewUrl=null;
        boolean Signal=false;

        if(Split.length>0) {
            if (!Split[0].equals("https:")) {

                if (!Split[0].equals("http:")) {
                    for (int i = 0; i < Split.length; i++) {
                        if (!Split[i].equals(MainDomain)) {
                            Signal = true;

                        }
                    }
                }

            }
        }
        if(Split2[Split2.length-1].contains("?"))
        {
            RepairLink =RepairLink.substring(0,RepairLink.indexOf("?"));
        }
        if(Signal)
        {
            NewUrl= MainDomain+"/"+RepairLink;
            return NewUrl;

        }
        else
        {
            return RepairLink;
        }





    }
}