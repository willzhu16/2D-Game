package main;

import entity.Entity;

public class CollisionChecker {
  GamePanel gp;

   public CollisionChecker(GamePanel gp) {
     this.gp = gp;
   }
   
   public void checkTile(Entity entity) {
     // Horizontal collision check
     checkHorizontalCollision(entity);
     // Vertical collision check
     checkVerticalCollision(entity);
 }

 public void checkHorizontalCollision(Entity entity) {
     int entityLeftWorldX = entity.worldX + entity.solidArea.x;
     int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
     int entityTopWorldY = entity.worldY + entity.solidArea.y;
     int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

     int entityLeftCol = entityLeftWorldX / gp.tileSize;
     int entityRightCol = entityRightWorldX / gp.tileSize;
     int entityTopRow = entityTopWorldY / gp.tileSize;
     int entityBottomRow = entityBottomWorldY / gp.tileSize;

     int tileNum1, tileNum2;

     if (entity.direction.equals("left")) {
         entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
         tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
         tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
         if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
             entity.collisionOn = true;
         }
     } else if (entity.direction.equals("right")) {
         entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
         tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
         tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
         if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
             entity.collisionOn = true;
         }
     }
 }

 public void checkVerticalCollision(Entity entity) {
     int entityLeftWorldX = entity.worldX + entity.solidArea.x;
     int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
     int entityTopWorldY = entity.worldY + entity.solidArea.y;
     int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

     int entityLeftCol = entityLeftWorldX / gp.tileSize;
     int entityRightCol = entityRightWorldX / gp.tileSize;
     int entityTopRow = entityTopWorldY / gp.tileSize;
     int entityBottomRow = entityBottomWorldY / gp.tileSize;

     int tileNum1, tileNum2;

     if (entity.direction.equals("up")) {
         entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
         tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
         tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
         if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
             entity.collisionOn = true;
         }
     } else if (entity.direction.equals("down")) {
         entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
         tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
         tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
         if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
             entity.collisionOn = true;
         }
     }
 }
}