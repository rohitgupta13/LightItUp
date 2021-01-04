# Slide Image Puzzle
<img src="https://blipthirteen.com/images/projects/light_it_up/1.png" alt="screenshot" height="400" />

Every level is stored in a HashMap

### Level 1
```
    levelMap.put("3,1", "3,0");
    levelMap.put("3,2", "1,0");
    levelMap.put("3,3", "1,0");
    levelMap.put("3,4", "1,0");
    levelMap.put("3,5", "3,180");
```

In every entry, the key stores the x and y coordinates,
and the value stores the type of block and it's solvable rotation

Every block is a clickable button

```
private void createAndPlaceButtons() {
        tileMap = new HashMap<String, Tile>();

        for (Map.Entry<String, String> entry : levelMap.entrySet()) {
            Vector2 position = getPosition(entry.getKey());
            String[] sa = entry.getValue().split(",");

            int randomRotation = MathUtils.random(0, 3);
            Gdx.app.log(randomRotation + " ", "" + randomRotation * 90);
            Tile tile = new Tile(entry.getKey(), sa[0], textureMap.get(sa[0]), position, randomRotation * 90);
            tileMap.put(entry.getKey(), tile);
        }

        // If somehow already complete, replace
        if (checkForCompletion()) {
            createAndPlaceButtons();
        }
    }
```

After every click we check to see if the blocks are in final state

```
private boolean checkForCompletion(){
        int completionSize = 0;

        for (Map.Entry<String, String> entry : levelMap.entrySet()) {
            String[] sa = entry.getValue().split(",");
            float rotation = Float.parseFloat(sa[1]);

            Tile t = tileMap.get(entry.getKey());
            float r = t.getRotation();
            if(r == 360){
                r = 0;
            }

            if(t.getId().equals("1")){
                if (rotation == 0 && (r == 0 || r == 180)){
                    //Gdx.app.log("" + t.getKey(), "complete");
                    completionSize++;
                }else if (rotation == 90 && (r == 90 || r == 270)){
                    completionSize++;
                }else{
                    Gdx.app.log("" + t.getKey(), "Not complete");
                }

            }else{
                if(rotation == r){
                    //Gdx.app.log("" + t.getKey(), "Complete");
                    completionSize++;
                }else{
                    Gdx.app.log("" + t.getKey(), "Not complete");
                }
            }
        }

        // If all blocks in final position
        if(completionSize == levelMap.size())
            return true;
        else return false;
    }

```
