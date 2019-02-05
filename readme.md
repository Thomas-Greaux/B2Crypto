# Cryptography ElGamal

This a simple, somewhat tested, ElGamal encryption scheme implementation.  
We only encode messages that are in the QR sub group of Zq, where q is a safe prime, in order to prevent the leaking of 1 bit of information.  

## Protocole de test

* generation de clés
* Déchiffrement du chiffrement est le plain texte
  * Vérifier que x est dans le groupe
* CPA
  * Be in a group of order prime
* m has to be in Qp
