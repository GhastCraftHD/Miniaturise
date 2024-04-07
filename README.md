![](https://github.com/GhastCraftHD/Miniaturise/blob/master/documentation/section_introduction_white.png?raw=true)

**Miniaturise** is a tool that allows you to create **miniatures** of something that you’ve built in Minecraft **without needing any mods**. You can also use it to work with single **Block Displays**. 

The miniatures are achieved through the new** Block Display** entities that were added in **Minecraft 1.19.4**. Block Displays are an **easy and performant** way to **display blocks** in your Minecraft worlds. One of their biggest advantages is that you can **change their size and rotation**.

Block Displays are not the only type of Display entities that were added in 1.19.4. For the other ones I have created **separate plugins**:
* Item Displays: [Showcase](https://hangar.papermc.io/GhastCraftHD/Showcase)
* Text Displays: [Holography](https://hangar.papermc.io/GhastCraftHD/Holography)

<img align="right" src="https://github.com/GhastCraftHD/Miniaturise/blob/master/documentation/section_installation_white.png?raw=true">

<p align="center">
<img src="https://github.com/GhastCraftHD/Miniaturise/blob/master/documentation/installation_guide_white.png?raw=true">
  </p>

Please notice the plugins [license](https://github.com/GhastCraftHD/Miniaturise/blob/master/LICENSE)

![](https://github.com/GhastCraftHD/Miniaturise/blob/master/documentation/section_config_white.png?raw=true)

You can alter the default configuration by changing the values of the `config.yml` file in the plugins data folder.

The setting max-entity-limit is defining how many Block Displays can be spawned when pasting a miniature.
**Be careful when increasing this setting as it will influence performance and might lead to crashes of servers or client when it is too high.**

```yaml
check-for-update: true
max-entity-limit: 1500
selector-tool : WOODEN_SHOVEL
adjuster-tool: BRUSH
```

<img align="right" src="https://github.com/GhastCraftHD/Miniaturise/blob/master/documentation/section_permissions_white.png?raw=true">

By default only server operators are able to use Miniaturise. If you want to give other players the right to use Miniaturise you need to grant them the permission `miniaturise.use`

![](https://github.com/GhastCraftHD/Miniaturise/blob/master/documentation/section_tutorial_white.png?raw=true)

Select two positions with either the selector tool (wooden shovel by default) or use the `/mposition` command.

If you have selected two positions use the `/mselect` command to select this region. This will also select any miniatures in that region as your placed miniature, so this way you can alter or delete already placed miniatures.

Now you can optionally use `/mscale selection <factor>` to scale your miniature before pasting it.

To paste your miniature simply use the `/mpaste` command

To adjust the position, size or rotation of your miniature you can use the adjuster tool (brush by default) together with the adjuster interface that you can open with `/madjust`

If you want to delete your miniature you can do this via the adjuster interface or by using `/mremove`

<img align="right" src="https://github.com/GhastCraftHD/Miniaturise/blob/master/documentation/section_saving_white.png?raw=true">

You can also save any miniature you’d like as a file with `/msave <filename>`

You can load miniatures from a file with `/msave <filename>`

You can list every saved miniature that are saved on your server with `/mlist`

The miniatures are saved in the data folder of Miniaturise in the miniatures folder.

`your-server/plugins/Miniaturise/miniatures`

![](https://github.com/GhastCraftHD/Miniaturise/blob/master/documentation/section_support_white.png?raw=true)

If you run into any issues feel free to report them to me on [GitHub](https://github.com/GhastCraftHD/Miniaturise/issues). There you can also suggest new features that could be added to Miniaturise.

<p align="center">
<a href="https://github.com/GhastCraftHD/Miniaturise/issues">
<img src="https://github.com/GhastCraftHD/Miniaturise/blob/master/documentation/github_button.png?raw=true">
</a></p>

<img align="right" src="https://github.com/GhastCraftHD/Miniaturise/blob/master/documentation/section_gallery_white.png?raw=true">

![](https://github.com/GhastCraftHD/Miniaturise/blob/master/miniaturise.jpg?raw=true)
