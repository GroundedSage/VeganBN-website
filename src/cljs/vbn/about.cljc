(ns vbn.about
  (:require [rum.core :as rum]
            [vbn.atoms :as atom]
            [vbn.molecules :as molecule]))

(rum/defc content []
  [:main#main
   (molecule/page-intro
    [:h1 "About"]
    [:p "To foster the vegan business community we host two different networking meetups every month."]
    [:p "One is purely social and the other boasts guest speakers or a discussion forum regarding pertinent topics for the vegan/vegan friendly business owner."])

   [:div.values-section
    {:style {:display "flex"
             :flex-direction "row"
             :align-items "baseline"}}
    [:h3

     ;{:style {:width "10em"}}
     "Values"]
    [:ol.values
     [:li
      [:span "Empathy"]
      [:p "Express empathy and kindness to all living things."]]
     [:li
      [:span "Be Inclusive"]
      [:p "Everyone has their story, their journey, a right to be hear and a right to the truth."]]
     [:li
      [:span "Positivity"]
      [:p "Positivity creates possibilities"]]
     [:li
      [:span "Kaizen"]
      [:p "Solve small problems and improve continuously.
A big problem is nothing other than a combination of small problems"]]
     [:li
      [:span "Quality"]
      [:p "Quality is always better than quantity"]]
     [:li
      [:span "Transparency"]
      [:p "Be open about process and clear in communitcation"]]

     [:li
      [:span "Collaboration"]
      [:p "Unite efforts because teh whole is always greater than the sum of it's parts."]]

     [:li
      [:span "On the shelf or off the shelf"]
      [:p "WILL BE CHANGING THIS ONE"]]

     [:li
      [:span "Impact"]
      [:p "Always consider both the local and global impact"]]
     [:li
      [:span "Inspire"]
      [:p "Be inspired and inspire others in evry way imaginable"]]]]])
