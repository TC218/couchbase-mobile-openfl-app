package;


import openfl.display.Sprite;
import openfl.text.TextField;
import openfl.text.TextFieldAutoSize;
import openfl.utils.JNI;


class Main extends Sprite {
	
  private static var testextension_dosomething : Dynamic;

  private static function init(){
      if (testextension_dosomething != null)
          return;
      testextension_dosomething = openfl.utils.JNI.createStaticMethod(
          "com.example.couchbaseopenflapp.MainActivity",
          "doSomething",
          "(Ljava/lang/String;)Ljava/lang/String;"
      );
  }

  public static function doSomething(str:String) : String {
      init();
      return testextension_dosomething(str);
  }

	
	public function new () {
		
		super ();
		
		
		var t = new TextField();
    t.autoSize = TextFieldAutoSize.LEFT;
    t.text = doSomething('abc');
    
    addChild(t);
    
	}
	
	
}