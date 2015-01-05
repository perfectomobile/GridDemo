package GridExe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.By;

public class repUtil {

	static public String Pre = "<!DOCTYPE html>"+
			"<html lang=\"en\" class=\"no-js\">"+
			"<head>"+
			"<meta charset=\"UTF-8\" />"+
			"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"> "+
			"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> "+
			"<title>MobileCloud</title>"+
			"<meta name=\"description\" content=\"MobileCloud: MobileCloud Continuous Quality Lab snapshots\" />"+
			"<meta name=\"author\" content=\"Perfecto Mobile\" />"+
			"<link rel=\"shortcut icon\" href=\"../favicon.ico\">"+
			"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/demo.css\" />"+
			"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/component.css\" />"+
			"<script src=\"js/modernizr.custom.js\"></script>"+
			"</head>"+
			"<body>"+
			"<div class=\"container\">"+
			"<header class=\"clearfix\">"+
			"<span>MobileCloud Continuous Quality Lab <span class=\"bp-icon bp-icon-about\" data-content=\"This report shows the various snapshots taken during the script execution.\"></span></span>"+
			"<h1>Snapshots</h1>"+
			"<nav>"+
			"<!--<a href=\"http://tympanus.net/Blueprints/FullWidthTabs/\" class=\"bp-icon bp-icon-prev\" data-info=\"previous Blueprint\"><span>Previous Blueprint</span></a>-->"+
			"<!--a href=\"\" class=\"bp-icon bp-icon-next\" data-info=\"next Blueprint\"><span>Next Blueprint</span></a-->"+
			"<!--<a href=\"http://tympanus.net/codrops/?p=18699\" class=\"bp-icon bp-icon-drop\" data-info=\"back to the Codrops article\"><span>back to the Codrops article</span></a>-->"+
			"<!--<a href=\"http://tympanus.net/codrops/category/blueprints/\" class=\"bp-icon bp-icon-archive\" data-info=\"Blueprints archive\"><span>Go to the archive</span></a>       -->"+
			"</nav>"+
			"</header>";	

	static public String Post = "<nav>"+
			"<span class=\"icon nav-prev\"></span>"+
			"<span class=\"icon nav-next\"></span>"+
			"<span class=\"icon nav-close\"></span>"+
			"</nav>"+
			"<div class=\"info-keys icon\">Navigate with arrow keys</div>"+
			"</section><!-- // slideshow -->"+
			"</div><!-- // grid-gallery -->"+
			"</div>"+
			"<script src=\"js/imagesloaded.pkgd.min.js\"></script>"+
			"<script src=\"js/masonry.pkgd.min.js\"></script>"+
			"<script src=\"js/classie.js\"></script>"+
			"<script src=\"js/cbpGridGallery.js\"></script>"+
			"<script>"+
			"new CBPGridGallery( document.getElementById( 'grid-gallery' ) );"+
			"</script>"+
			"</body>"+
			"</html>";

	static public String preGrid = "<div id=\"grid-gallery\" class=\"grid-gallery\">"+
			"<section class=\"grid-wrap\">"+
			"<ul class=\"grid\">"+
			"<li class=\"grid-sizer\"></li><!-- for Masonry column width -->";

	static public String postGrid = "</ul>"+
			"</section><!-- // grid-wrap -->";

	static public String preSlides = 
			"<section class=\"slideshow\">"+
					"<ul>";


	static public String postSlides = "</ul>";


	public static void createReport()
	{
		File f = new File("c:\\test\\rep\\a.html") ;
		FileWriter output;
		try {
			output = new FileWriter(f);
			output.write(Pre);
			output.write(preGrid);
			output.write(addLineGrid("img/thumb/rwdTestchromeBefore.jpg", "Chrome", "beforeUser"));
			output.write(addLineGrid("img/thumb/rwdTestchromeAfter.jpg", "Chrome", "AfterUser"));
		
			output.write(addLineGrid("img/thumb/rwdTestfirFoxBefore.jpg", "FireFox", "beforeUser"));
			output.write(addLineGrid("img/thumb/rwdTestfirFoxAfter.jpg", "FireFox", "AfterUser"));
			
			output.write(addLineGrid("img/thumb/rwdTestPMBefore.jpg", "PM", "beforeUser"));
			output.write(addLineGrid("img/thumb/rwdTestPMAfter.jpg", "PM", "AfterUser"));
		
		
			output.write(postGrid);
			output.write(preSlides);
			
			output.write(addLineSlideshow("img/thumb/rwdTestchromeBefore.jpg", "Chrome", "beforeUser"));
			output.write(addLineSlideshow("img/thumb/rwdTestchromeAfter.jpg", "Chrome", "AfterUser"));

			output.write(addLineSlideshow("img/thumb/rwdTestfirFoxBefore.jpg", "FireFox", "beforeUser"));
			output.write(addLineSlideshow("img/thumb/rwdTestfirFoxAfter.jpg", "FireFox", "AfterUser"));
			
			output.write(addLineSlideshow("img/thumb/rwdTestPMBefore.jpg", "PM", "beforeUser"));
			output.write(addLineSlideshow("img/thumb/rwdTestPMAfter.jpg", "PM", "AfterUser"));
		
			output.write(postSlides);
	 		output.write(Post);

	 		output.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

 		
	}

	public static String addLineGrid(String image,String Device,String title)
	{

		// image under img/thumb/7.jpg
		String gridLine=
				"<li>"+
						"<figure>"+
						"<img src=\""+image+"\" alt=\"img01\"/>"+
						"<figcaption><h3>"+Device+"</h3><p>"+title+"</p></figcaption>"+
						"</figure>"+
						"</li>";

		return gridLine;
	}
	public static String addLineSlideshow(String image,String Device,String title)
	{
		String gridSlides=				
				"<li>"+
						"<figure>"+
						"<figcaption>"+
						"<h3>"+Device+"</h3>"+
						"<p>"+title+"</p>"+
					//	"<p>Size: 1024 X 768</p>"+
						"</figcaption>"+
						"<img src=\""+image+"\" alt=\"img01\" />"+
						"</figure>"+
						"</li>";	
		return gridSlides;	
	}

}
