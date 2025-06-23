package org.example.db;


public class GData {
    public static final String DEFAULT_SOURCE           = "./src/main/resources/sources/";
    public static final String RESOURCES                = "./src/main/resources/";

    public static final int MAX_VAR_LENGHT              = 20;
    public static final String tab = "  ";

    private static final ThreadLocal<GData> instance = ThreadLocal.withInitial(GData::new);


    private static eLang lang = eLang.RU;
    private static String projectName = "default";
    private static String targetFolder;

    private GData() {}

    public static GData getInstance() {
        return instance.get();
    }

    public void clear() {
        instance.remove();
    }

    static public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    static public String getTargetFolder() {
        return targetFolder;
    }

    public void setTargetFolder(String targetFolder) {
        this.targetFolder = targetFolder;
    }


    static public eLang getLang() {
        return lang;
    }

    public void setLang(eLang lang) {
        this.lang = lang;
    }

}
