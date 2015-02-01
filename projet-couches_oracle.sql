DROP TABLE Produit
DROP TABLE Catalogue


CREATE TABLE Catalogue
(
    idCatalogue NUMBER(5),
    nomCatalogue VARCHAR2(50),
    CONSTRAINT pk_Catalogue 
        PRIMARY KEY (idCatalogue)
);

CREATE SEQUENCE seqCat INCREMENT BY 1;

CREATE TABLE Produit
(
    idProduit VARCHAR(5), 
    nomProduit VARCHAR2(25), 
    stockProduit NUMBER(5), 
    prixHTProduit NUMBER(5) , 
    catalogue NUMBER(5),
    CONSTRAINT fk_Produits_catalogue 
        FOREIGN KEY(catalogue) REFERENCES Catalogue (idCatalogue),
    CONSTRAINT pk_Produits 
        PRIMARY KEY (idProduit)
) ;
    

CREATE SEQUENCE seqProd INCREMENT BY 1;
