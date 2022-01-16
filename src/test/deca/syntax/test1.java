public class test1
{
    public static void main(String[] args)
    {
        int a ;
        int b =0;
        int c =0;
        int i =0;
        int quo1= 1;
        int diviseur =1;
        int compteur;
        int quo2= 1;
        a = 123321;
        boolean estPalindrome = true;
        compteur =0;
        while(estPalindrome){
            
            //System.out.println("Entrer un entier : ");
            
            while(compteur != 3)
            {
                while(i != compteur+1)
                {
                    quo1 = quo1*10;
                    i = i+1;
                    System.out.println("quo1 "+quo1+" cmp "+compteur+" i "+i);
                }
                i = 0;
                while(i != 5-compteur)
                {
                    quo2 = quo2*10;
                    i = i+1;
                    System.out.println("quo2 "+quo2+" cmp "+compteur+" i "+i);
                }
                i=0;
    
                b = (a/quo2)%10;
                c = (a%quo1)/diviseur;
                diviseur = diviseur*10;
                System.out.println(c +":::c:::::b::"+b);
                quo1 = 1;
                quo2 = 1;
                if(b != c){
                    System.out.println("le nombre n'est pas palaindrome  ");
                    compteur = 3;
                    estPalindrome=false;
                }
                else{
                    ++compteur;
                    if(compteur != 3){
                        
                        System.out.println("le nombre cmpt "+compteur);
                    }
                    else{
                        System.out.println("le nombre est palaindrome  ");
                        estPalindrome = false;
                    }
                }
                System.out.println("le nombre cmpt "+compteur);
            }
            
        }
    }
    
}