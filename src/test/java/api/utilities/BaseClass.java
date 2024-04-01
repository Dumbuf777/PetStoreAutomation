/**
 * 
 */
package api.utilities;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * @author shrikrushna.sonkar
 *
 */
public class BaseClass extends ExtentManager{

	
	@BeforeClass
	public void setup() {
		extentSetup();
	
	}
	
	@AfterClass
	public void flush() {
		extent.flush();
	}


}
