DROP TABLE Produit

CREATE TABLE Produit
(idProduit VARCHAR(5), nomProduit VARCHAR(25), stockProduit NUMBER(5), prixHTProduit NUMBER(5) ,
CONSTRAINT pk_Produits PRIMARY KEY (idProduit)) ;
    

CREATE SEQUENCE seqProd INCREMENT BY 1;
