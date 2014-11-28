import java.util.*;
import java.lang.*;
import java.math.*;
class encryption
{
	keyGen gen= new keyGen(1024);
	BigInteger encryptedText;
	BigInteger e= gen.returnE();
	BigInteger n= gen.returnN();
	String decryptedText;
	encryption(BigInteger M)
	{
		encryptedText= M.modPow(e,n);
	}
	decryption(BigInteger M)
	{
		
	}
}