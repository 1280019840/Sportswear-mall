# Project Introduction
* [Editor's Note](README.md)
#### system development adopts front end separation:
The design of this system mainly starts with the needs of people who love sports and people who like to wear sports clothes, and designs and implements a web-based platform. Opening a professional sportswear mall is an effective way to meet people's shopping needs. Users can browse directly and efficiently, and the system helps consumers save time and cost. Make it more convenient to shop in a fast-paced life. <br>

The requirements of users and system administrators are briefly analyzed below. The main design modules of the user include user login and registration module, user center module, address management module, shopping cart module, browsing product module, viewing product details module, viewing product comments module, fitness discussion area module, and product classification module. <br>

The main design modules of the administrator include the administrator login module, commodity management module, user management module, fitness discussion area management module, announcement management module, statistical data module, commodity classification management module, and rotation chart management module. <br>

The role of the system is divided into users and system administrators, the system adopts the current popular SpringBoot framework at the back end, the front-end interaction effect uses the Vue framework, the database uses MySQL, through the above three key technologies to complete a complete system, the technical realization of the system
* SpringBoot
* Vue.js
* MySQL
* ElementUI
* Ajax
* MyBatisPlus
* Jwt等等

## File introduction
#### The SpringBoot file on the back-end is as follows:
* comm: used to set the code returned by the request, and set the return result, etc. <br>
* config: Used to set some system configuration, such as MybatisPlus configuration, etc. <br>
* controller: Controller folder <br>
* entity: Entity folder <br>
* mapper: Persistent Layer interface folder <br>
* service: Business folder <br>
* utils: Some custom tool files <br>

![image](https://github.com/1280019840/Sportswear-mall/raw/main/img/SpringBoot.png)

#### The client files are as follows:

![image](https://github.com/1280019840/Sportswear-mall/raw/main/img/vue-user.png)

#### Background administrator files are as follows:

![image](https://github.com/1280019840/Sportswear-mall/raw/main/img/vue-admin.png)

#### The database table is as follows:

![image](https://github.com/1280019840/Sportswear-mall/raw/main/img/MySQL.png)

## Use:
* Java language environment
* MySQL Database
* Vue installation
#### Back-end projects:
1. Clone the project to a local directory
` ` `
gi clone https://github.com/1280019840/Sportswear-mall.git
` ` `
2. Access the back-end directory:
` ` `
cd admin-main
` ` `
3. Update the dependencies of the pox.xml file
4. Configure information such as the database and image storage location

#### Client:
1. Access the project directory
` ` `
cd vue-index
` ` `
2. Install dependencies
` ` `
npm install
` ` `
3. Start the project
` ` `
npm run dev
` ` `

#### Background administrator:
1. Access the project directory
` ` `
cd vue-admin
` ` `
2. Install dependencies
` ` `
npm install
` ` `
3. Start the project
` ` `
npm run serve
` ` `

## Effect display
![image](https://github.com/1280019840/Sportswear-mall/raw/main/img/.png)
