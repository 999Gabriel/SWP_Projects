package com.example.museum_new;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class MuseumController
{
    @FXML
    private AnchorPane rootPane;

    private PerspectiveCamera camera;
    private Group root3D;

    private double cameraX = 0;
    private double cameraY = 0;
    private double cameraZ = 0;
    private double cameraRotateY = 0;
    private static final double CAMERA_SPEED = 10;

    @FXML
    public void initialize() {
        root3D = new Group();

        Scene scene = new Scene(root3D, 800, 600, true);
        scene.setFill(Color.SKYBLUE);

        camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll(
                new Rotate(0, Rotate.X_AXIS),
                new Translate(cameraX, cameraY, cameraZ)
        );
        scene.setCamera(camera);

        rootPane.getChildren().add(new Group(scene.getRoot()));

        addRoomWithTexture();

        setupControls(scene);
        startAnimation();
    }

    private void addRoomWithTexture() {
        Image image = new Image(getClass().getResource("/img/IMG_8901.JPG").toExternalForm());

        PhongMaterial wallMaterial = new PhongMaterial();
        wallMaterial.setDiffuseMap(image);

        PhongMaterial floorMaterial = new PhongMaterial();
        floorMaterial.setDiffuseMap(image);

        PhongMaterial ceilingMaterial = new PhongMaterial();
        ceilingMaterial.setDiffuseMap(image);

        double width = 1000;
        double height = 600;
        double depth = 1000;

        Box floor = new Box(width, 10, depth);
        floor.setMaterial(floorMaterial);
        floor.setTranslateY(height / 2 - 5);

        Box ceiling = new Box(width, 10, depth);
        ceiling.setMaterial(ceilingMaterial);
        ceiling.setTranslateY(-height / 2 + 5);

        Box leftWall = new Box(10, height, depth);
        leftWall.setMaterial(wallMaterial);
        leftWall.setTranslateX(-width / 2 + 5);

        Box rightWall = new Box(10, height, depth);
        rightWall.setMaterial(wallMaterial);
        rightWall.setTranslateX(width / 2 - 5);

        Box frontWall = new Box(width, height, 10);
        frontWall.setMaterial(wallMaterial);
        frontWall.setTranslateZ(-depth / 2 + 5);

        Box backWall = new Box(width, height, 10);
        backWall.setMaterial(wallMaterial);
        backWall.setTranslateZ(depth / 2 - 5);

        root3D.getChildren().addAll(floor, ceiling, leftWall, rightWall, frontWall, backWall);

        // Kamera vor dem vorderen Wand zentrieren
        camera.getTransforms().add(new Translate(0, 0, -depth / 2 - 200));
    }

    private void setupControls(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.W) {
                camera.getTransforms().add(new Translate(0, 0, CAMERA_SPEED));
            } else if (event.getCode() == KeyCode.S) {
                camera.getTransforms().add(new Translate(0, 0, -CAMERA_SPEED));
            } else if (event.getCode() == KeyCode.A) {
                camera.getTransforms().add(new Translate(CAMERA_SPEED, 0, 0));
            } else if (event.getCode() == KeyCode.D) {
                camera.getTransforms().add(new Translate(-CAMERA_SPEED, 0, 0));
            } else if (event.getCode() == KeyCode.LEFT) {
                cameraRotateY -= 5;
                camera.getTransforms().add(new Rotate(-5, Rotate.Y_AXIS));
            } else if (event.getCode() == KeyCode.RIGHT) {
                cameraRotateY += 5;
                camera.getTransforms().add(new Rotate(5, Rotate.Y_AXIS));
            }
        });
    }

    private void startAnimation() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                camera.getTransforms().clear();
                camera.getTransforms().addAll(
                        new Rotate(cameraRotateY, Rotate.Y_AXIS),
                        new Translate(cameraX, cameraY, cameraZ)
                );
            }
        };
        timer.start();
    }
}
