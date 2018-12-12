package model;

public class FooterInfoModel implements ModelInterface {
    /**Singleton Object creation ensures there is only a single instance created which is to be reused**/

    private static FooterInfoModel footerInfoModel = new FooterInfoModel();
    private FooterInfoModel() {
    }

    /**+
     * Function return an instance of FooterInfoModel Class
     * @return new FooterInfoModel()
     */
    public static FooterInfoModel getFooterInfoModel() {
        return footerInfoModel;
    }

    public static void setFooterInfoModel(FooterInfoModel footerInfoModel) {
        FooterInfoModel.footerInfoModel = footerInfoModel;
    }
}
