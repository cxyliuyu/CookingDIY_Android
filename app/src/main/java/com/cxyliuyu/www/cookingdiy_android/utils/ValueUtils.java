package com.cxyliuyu.www.cookingdiy_android.utils;

/**
 * Created by ly on 2016/3/6.
 */
public class ValueUtils {
    private static String SERVER = "http://114.215.135.70/lypublic/1/index.php";
    public static String key = "E5240C4307AA500300BAA94F9E31CEFD";
    public static String loginURL = "http://114.215.135.70/lypublic/1/index.php/CaiApi/User/login";
    public static String LOGTAG = "COOKINGTAG";
    public static String USERID = "USERID";
    public static String USERPASSWORD = "USERPASSWORD";
    public static String USERNAME = "USERNAME";
    public static String USERIMG = "USERIMG";
    public static String USERTRUENAME = "USERTRUENAME";
    public static String ISLOGIN = "ISLOGIN";
    public static String refreshmefragment = "com.cxyliuyu.cookingdiy.refreshmefragment";
    public static String GETFOODBYPAGE = SERVER + "/CaiApi/Food/getFoodsByPage";
    public static String GETFOODBYID = SERVER + "/CaiApi/Food/getFoodById";
    public static String SEARCHFOODS = SERVER + "/CaiApi/Food/searchFood";
    public static String ISSAVED = SERVER + "/CaiApi/Save/isSaved";
    public static String DELETESAVE = SERVER + "/CaiApi/Save/deleteSave";
    public static String ADDSAVE = SERVER + "/CaiApi/Save/addSave";
    public static String GETSAVEBYPAGE = SERVER + "/CaiApi/Save/getSaveByUserIdAndPage";
    public static String REGISTER = SERVER + "/CaiApi/User/register";
    public static String GETCOMMENTBYFOODIDANDPAGE = SERVER+"/CaiApi/Comment/getCommentByfoodIdAndPage";
    public static String ADDCOMMENT = SERVER + "/CaiApi/Comment/addComment";
    public static String GETFOODSBYUSERID = SERVER + "/CaiApi/Food/getFoodsByUserIdAndPage";
    public static String GETCONVERSATION = SERVER + "/CaiApi/Comment/getConversations";
    public static String UPLOADFILE = SERVER + "/CaiApi/File/upload";
    public static String UPLOADDIR = "http://114.215.135.70/lypublic/1/Uploads/";
    public static String ADDFOOD = SERVER + "/CaiApi/Food/addFood";
    public static String ADDFOODSTEP = SERVER + "/CaiApi/Food/addFoodStep";
    public static String ADDFOODLIST = SERVER + "/CaiApi/Food/addFoodList";
}
