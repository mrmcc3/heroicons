# Heroicons as Hiccup Data

```clojure
(require '[mrmcc3.heroicons.outline.md :as heroicons])

(heroicons/arrow-circle-right) ;; =>
[:svg
 {:fill "none", :viewbox "0 0 24 24", :stroke "currentColor"}
 [:path
  {:stroke-linecap "round",
   :stroke-linejoin "round",
   :stroke-width "2",
   :d "M13 9l3 3m0 0l-3 3m3-3H8m13 0a9 9 0 11-18 0 9 9 0 0118 0z"}]]

(heroicons/shield-check {:class "h-5"}) ;; =>
[:svg
 {:fill "none", :viewbox "0 0 24 24", :stroke "currentColor", :class "h-5"}
 [:path
  {:stroke-linecap "round",
   :stroke-linejoin "round",
   :stroke-width "2",
   :d "M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z"}]]
```

# Heroicons

A set of free MIT-licensed high-quality SVG icons for you to use in your web projects.

140 icons per style currently, more to come in the future.

### Outline style

24x24 icons drawn with a stroke.

![](./.github/outline-md-preview.svg)

### Solid style

Smaller 20x20 icons drawn with fills.

![](./.github/solid-sm-preview.svg)

### Usage

To use these icons, simply copy the source for the icon you need and inline it directly into your HTML:

```html
<svg class="h-6 w-6 text-gray-500" fill="none" viewBox="0 0 24 24" stroke="currentColor">
  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z"/>
</svg>
```

Both icon styles are preconfigured to be stylable by setting the `color` CSS property, either manually or using utility classes like `text-gray-500` in a framework like [Tailwind CSS](https://tailwindcss.com).