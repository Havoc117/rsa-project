import java.util.*;
import java.security.*;
import java.lang.*;
import java.math.*;
class keyGen
{
	SecureRandom secRand= new SecureRandom();
    BigInteger q;
    BigInteger p;
    BigInteger n;
    BigInteger phiQ;
    BigInteger phiP;
    BigInteger phiN;
    BigInteger tempE;
    BigInteger e;
    BigInteger d;
    BigInteger negOne= new BigInteger("-1");
    BigInteger one= new BigInteger("1");
    int comp;
    boolean prime;
	keyGen(int keySize)
	{
    	p= BigInteger.probablePrime(keySize, secRand);
    	q= BigInteger.probablePrime(keySize, secRand);
    	n=p.multiply(q);
    	phiQ= q.add(one);
    	phiP= p.add(one);
    	phiN= phiQ.multiply(phiP);
  		do 
  		{
    		e= BigInteger.probablePrime(keySize, secRand);
    		comp= e.compareTo(phiN);
  		} while (comp!=-1);
  		d= e.modInverse(phiN);
	}
	
	BigInteger returnP()
	{
		return p;
	}
	BigInteger returnQ()
	{
		return q;
	}
	BigInteger returnN()
	{
		return n;
	}
	BigInteger returnPhiP()
	{
		return phiP;
	}
	BigInteger returnPhiQ()
	{
		return phiQ;
	}
	BigInteger returnPhiN()
	{
		return phiN;
	}
	BigInteger returnE()
	{
		return e;
	}
	BigInteger returnD()
	{
		return d;
	}	
}