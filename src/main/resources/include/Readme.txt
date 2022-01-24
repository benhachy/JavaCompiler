- Le dossier TRIGO en Java, contient : 

    ++ le code en JAVA de l'extension TRIGO, c'est presque le même que celui en Deca, les seules différences sont l'utilisation de static en java, le elseif qui est attaché en Deca, et l'uilisation de la fonction atan de la bib Math de Java pour générer les 46 valeurs qui sont déjà stockés dans une fonction en Math.decah, hormis ça le code est pareil. Lors du codage Java on a utilisé les opérations les plus basiques possibles ! 

    ++ Les tests de précision avec Junit5 pour toutes les fonctions (pas que les cinq demandées)
    
    ++ Un fichier pom.xml qui automatise les tests et la compilation, on pourra executer donc les commandes mvn test et mvn compile
           en fait le dossier TRIGO en Java peut être vu comme un projet maven indépendant avec ses propres tests.
           
    ++ Comme notre compilateur présente des petits beugs au niveau objet, on a fait une version sans objet
    du fichier Math.decah présenté sous forme de cinq fichiers nommés Test_Fonction_Sans_Objet pour les 
    cinq fonctions demandées.
    
    ++ Chaque fichier a une valeur par défaut pour laquelle il affichera la valeur en l'executant : 
       Les valeurs d'entrée pour les fonctions suivantes sont : 
       
        	++ cos :  PI/4		(stocké dans le float theta)
        	++ sin :  PI/4		(stocké dans le float theta)
        	++ atan : 0.577350f     (stocké dans le float theta)
        	++ asin : 0.5f        	(stocké dans le float theta)
        	++ ulp :  20.56546f   (stocké dans le int d)
        	
    ++ Ces valeurs peuvent être bien adapté selon notre choix :
    
    		++ cos : [-PI/2, PI/2]
    		++ sin : [-PI/2, PI/2]
    		++ atan : [-1,1]
    		++ asin : [-1/sqrt(2),1/sqrt(2)]
        	++ ulp : tout les nombres
        		
    ++ En version objet les fonctions foncionnent pour toute entrée de leur domaine de défintion.
