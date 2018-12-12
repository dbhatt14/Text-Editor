package model;

/**+
 * Class that holds the logic for the file stack execution for tracing every cut/copy/paste action performed
 */
public class EditStackModel implements ModelInterface {
    /**Singleton Object creation ensures there is only a single instance created which is to be reused**/

    private static EditStackModel editStackModel = new EditStackModel();
    private EditStackModel() {
    }

    /**+
     * Function return an instance of EditStackModel Class
     * @return new EditStackModel()
     */
    public static EditStackModel getEditStackModel() {
        return editStackModel;
    }

    public static void setEditStackModel(EditStackModel editStackModel) {
        EditStackModel.editStackModel = editStackModel;
    }
}
