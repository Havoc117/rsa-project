import java.nio.*;
class padding
{
	byte[] file =
	padding(String a)
	{
		Path path= Paths.get(a);
		file= Files.readAllBytes(path);
	}
	byte[] getFile()
	{
		return file;	
	}
}