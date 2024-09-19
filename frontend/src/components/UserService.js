class UserService {
    static user = null;
  
    static setUser(user) {
      this.user = { ...user }; 
    }
  
    static getUser() {
      return this.user;
    }
  }
  
  export default UserService;
  