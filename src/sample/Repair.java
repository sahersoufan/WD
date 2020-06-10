package sample;



public class Repair {



    public static String RepairUrl(String MainUrl, String RepairLink)  {
        String Split[]=RepairLink.split("/");
        String Split1[]=MainUrl.split("/");
        String Split2[]=RepairLink.split("\\.");
        String NewUrl=null;
        boolean Signal=false;

        if(Split.length>0) {
            if (!Split[0].equals("https:")) {

                if (!Split[0].equals("http:")) {
                    for (int i = 0; i < Split.length; i++) {
                        if (!Split[i].equals(Split1[2])) {
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
            NewUrl=Split1[0]+"//"+ Split1[2]+"/"+RepairLink;
            return NewUrl;

        }
        else
        {
            return RepairLink;
        }





    }
}
