public class Child extends Parent implements IParent {

    @Override
    public void getAddress() {
       System.out.print(" Child class overrides the Parent class getAddress()");
    }

    @Override
    public void getMobileNumber() {
        System.out.print(" Child class overrides the Parent class getMobileNumber()");
    }

    @Override
    public void getAge() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}