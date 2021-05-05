from user import User
from post import Post

aUser = User("sd@sd.com", "Sertac", "changeme", "developer")
aUser.get_user_info()

aUser.change_job_title("full stack developer")
aUser.get_user_info()

aPost = Post("a new message", aUser.name)
aPost.get_post_info()
