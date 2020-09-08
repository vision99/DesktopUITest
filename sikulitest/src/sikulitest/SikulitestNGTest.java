/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sikulitest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.sikuli.basics.Settings;
import org.sikuli.script.App;
import org.sikuli.script.Key;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Mouse;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Sikulix;
import static org.testng.Assert.*;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import specobj.DeleteNodeComfirm;
import specobj.PlusMinus;

/**
 *
 * @author alexd
 */
public class SikulitestNGTest {

    private static final Logger LOGGER = Logger.getLogger(SikulitestNGTest.class);

    public static final String mainMenu[] = new String[]{"otcheti", "actions", "dannie", "filemenu", "pokazateli", "reference", "settings", "spravochniki", "window"};
    public static final String projectDir = new File("").getAbsolutePath();
    public static final String imgDir = projectDir + "\\img";
    public static final String mainmenuDir = imgDir + "\\mainmenu";
    public static final String windowDir = imgDir + "\\window";
    public static final String pokazateliDir = mainmenuDir + "\\pokazateli";
    public static final String specobjDir = imgDir + "\\spacialobjects";
    public static final String mainselectDir = pokazateliDir + "\\mainselect";

    public static final String menuSectionsDir = projectDir + "\\menusections\\";
    public static final String newIndicator = "I_A08648([Value,<>].&[1987])  +7 + 9";
    public static final String newDate = "01.01.2019";

    public static float pictureSimilairity = 0.99f;
    public static float menuSimilairity = 0.58f;
    public static App app = new App("C:\\NBRB\\Client\\Nbrb.Client.exe");
    public Pattern ptrn;
    public Region appWindow = null;
    public Map<String, int[]> vkl = new HashMap<>();
    public static Map<String, int[]> pl = new HashMap<>();
    public double sec = 2;
    public static PlusMinus p_obj = null;
    public static DeleteNodeComfirm delnodecomf_obj = null;

    static {
        Settings.AutoWaitTimeout = 10f;
        Settings.MoveMouseDelay = 0.5f;
        Settings.ClickDelay = 0.5f;
        System.setProperty("window.dir", windowDir);
        System.setProperty("mainmenu.dir", mainmenuDir);
        System.setProperty("pokazateli.dir", pokazateliDir);
        System.setProperty("specobj.dir", specobjDir);
        System.setProperty("mainselect.dir", mainselectDir);
    }

    public SikulitestNGTest() {

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        pl.put("plus", new int[]{-16, 0});
        pl.put("minus", new int[]{16, 0});
        p_obj = new PlusMinus(PatternFinder.findPattern("specobj.dir", "plusminus"), pl);
        pl.put("pressOk", new int[]{106, 52});
        pl.put("pressNo", new int[]{176, 52});
        delnodecomf_obj = new DeleteNodeComfirm(PatternFinder.findPattern("specobj.dir", "deletenode"), pl);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        Sikulix.popup("finish", "end");
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {

        if (Sikulitest.app.isRunning()) {
            Sikulitest.app.focus();
            appWindow = Sikulitest.app.window();
        } else {
            Sikulitest.app.open(10);
            Thread.sleep(5000);
            Sikulitest.app.focus();
            appWindow = Sikulitest.app.window();
        }
//        appWindow.saveScreenCapture(projectDir);
        assertNotNull(appWindow, "App is not running");
        assertTrue(app.isRunning(), "App is not running");
//        System.exit(0);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of main method, of class Sikulitest.
     */
    @Test(groups = {"maingroup"}, enabled = false)
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        Sikulitest.main(args);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    @Test(groups = {"initwindow"}, enabled = false)
    public void initWindow() throws InterruptedException {
        String dir = "window.dir";
        Match tmp3 = appWindow.exists(PatternFinder.findPattern(dir, "expandred"));

        if (tmp3 != null) {
            tmp3.highlight(5f);
            System.out.println("App is opend correctly!");
            Reporter.getCurrentTestResult().setStatus(ITestResult.SUCCESS);
            return;
        }

        ptrn = PatternFinder.findPattern(dir, "collapsered");

        Match mtch = appWindow.exists(ptrn);
        if (mtch != null) {
            mtch.highlight(3f);
        }
        Match tmp = null;
        assertNotNull(mtch, "collapsered not found");
        tmp = mtch.exists(PatternFinder.findPattern(dir, "coll"));
        if (tmp != null) {
            tmp.highlight(3f);
        }
        assertNotNull(tmp, "coll found");
        tmp.click();

        mtch = app.window().exists(PatternFinder.findPattern(dir, "expandred"));
        if (mtch != null) {
            mtch.highlight(3f);
        }
        assertNotNull(mtch, "expandred found");

        tmp = mtch.exists(PatternFinder.findPattern(dir, "exp"));
        if (tmp != null) {
            tmp.highlight(3f);
        }
        assertNotNull(tmp, "exp found");
        Thread.sleep(2000);
    }

    @Test(groups = {"initwindow"}, dependsOnMethods = {"initWindow"}, enabled = false)
    public void checkDefaultWindow() {
//        app.window().saveScreenCapture(projectDir);
//        System.exit(0);
        Match mtch = app.window().exists(PatternFinder.findPattern("window.dir", "defaultwind"));

        assertNotNull(mtch, "env is not initialize!!!");
        System.out.println("Env initialize");
        LOGGER.debug("Env initalize");
    }

    @Test(groups = {"inout"}, dependsOnGroups = {"initwindow"}, enabled = false)
    public void inout() throws InterruptedException {
        vkl.put("extend", new int[]{-10, 0});
        vkl.put("collapse", new int[]{0, 0});
        vkl.put("cross", new int[]{30, 0});

        VklVikl tmp = new VklVikl(PatternFinder.findPattern("window.dir", "expandred"), vkl);

        app.window().exists(tmp.collapse()).click();
        Pattern tempPat = PatternFinder.findPattern("window.dir", "collapsered");
        app.window().exists(tmp.change(tempPat)).click();
    }

    @Test(/*dependsOnGroups = {"initwindow"}*/)
    public void newIndicator() throws InterruptedException {
        String dir = "pokazateli.dir";
        app.window().exists(PatternFinder.findPattern("mainmenu.dir", "mainmenu"))
                .highlight(sec)
                .exists(PatternFinder.findPattern("mainmenu.dir", "pokazateli"))
                .highlight(sec)
                .click();
        app.window().exists(PatternFinder.findPattern(dir, "pokazateli"))
                //                .highlight(sec)
                .exists(PatternFinder.findPattern(dir, "pokazatelim").targetOffset(-150, 0))
                //                .highlight(sec)
                .click();
        app.window().exists(PatternFinder.findPattern(dir, "ART-plain"))
                .highlight(sec)
                .doubleClick();

        app.window().exists(PatternFinder.findPattern(dir, "ZZZ_ART_03_plain"))
                .highlight(sec)
                .doubleClick();
        Region free = app.window().exists(PatternFinder.findPattern(dir, "dates"));
        System.out.println("free = " + free);
//        System.exit(0);
        while (app.window().exists(PatternFinder.findPattern(dir, "dates")) == null) {
            free = app.window().exists(PatternFinder.findPattern(dir, "spo")).below(20).highlight(sec);
            free.click();
            free = app.window().exists(PatternFinder.findPattern(dir, "spo")).above(40).highlight(sec);
//            free.saveScreenCapture(projectDir);
            free.exists(p_obj.minus()).highlight(sec).click();

            app.window().exists(delnodecomf_obj.pressOk()).click();
            Thread.sleep(1000);
        }
        app.window().exists(PatternFinder.findPattern(dir, "dates")).saveScreenCapture(projectDir);
        app.window().exists(p_obj.plus()).highlight(sec).click();
        free = app.window().exists(PatternFinder.findPattern(dir, "formula")).highlight(sec);
        free.click();
        free.type("a", Key.CTRL);
        free.paste(newIndicator);
        dir = "mainselect.dir";
        System.out.println(mainselectDir);
        
        app.window().exists(PatternFinder.findPattern(dir, "mainselect")).highlight(imgDir).click();
        free = app.window().exists(PatternFinder.findPattern(dir, "skobup"));
        while (free != null) {
            free.click();
            free = app.window().exists(PatternFinder.findPattern(dir, "skobup"));
        }
        app.window()
                .exists(PatternFinder.findPattern(dir, "chooseallwhite"))
                .below(150)
                .grow(0,0,17,0)
                .saveScreenCapture(projectDir);
        app.window()
                .exists(PatternFinder.findPattern(dir, "collapsemenuclear"))
                .highlight(sec);
        
        Mouse.move(new Location(300,500));
    }
}
