package cn.gulu.model.request;

import java.util.List;

public class LoginBean {

    /**
     * Status : 1
     * phone : 15941218432
     * GangWei : 请求
     * Sex : 男
     * SystemID : null
     * Id : 4
     * AddTime : 2021-03-31 13:08:05
     * Units : 北京
     * Name : 李四
     * Password : 123456
     */

    private List<DengLuBean> dengLu;

    public List<DengLuBean> getDengLu() {
        return dengLu;
    }

    public void setDengLu(List<DengLuBean> dengLu) {
        this.dengLu = dengLu;
    }

    public static class DengLuBean {
        private int Status;
        private String phone;
        private String GangWei;
        private String Sex;
        private Object SystemID;
        private int Id;
        private String AddTime;
        private String Units;
        private String Name;
        private String Password;

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getGangWei() {
            return GangWei;
        }

        public void setGangWei(String GangWei) {
            this.GangWei = GangWei;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public Object getSystemID() {
            return SystemID;
        }

        public void setSystemID(Object SystemID) {
            this.SystemID = SystemID;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getAddTime() {
            return AddTime;
        }

        public void setAddTime(String AddTime) {
            this.AddTime = AddTime;
        }

        public String getUnits() {
            return Units;
        }

        public void setUnits(String Units) {
            this.Units = Units;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }
    }
}
